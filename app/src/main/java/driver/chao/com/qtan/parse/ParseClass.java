package driver.chao.com.qtan.parse;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import driver.chao.com.qtan.APIClient;
import driver.chao.com.qtan.bean.MainBean;
import driver.chao.com.qtan.bean.NBean;
import driver.chao.com.qtan.bean.OBean;
import driver.chao.com.qtan.bean.RBean;
import driver.chao.com.qtan.bean.YBean;
import driver.chao.com.qtan.util.ParserUtil;
import driver.chao.com.qtan.util.TanCompleteListener;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by aaron on 2018/7/23.
 * 解析数据
 */
public class ParseClass {
    public static TanCompleteListener tanCompleteListener = null;
    public static Subscription subscription = null;
    private static APIClient apiClient = new APIClient();
    private static ArrayList<MainBean> mDataList = new ArrayList();
    private static boolean isFinish = false;

    public static void parseMainData(final Document doc, final int type) {
        subscription = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long count) {
                        Log.i("MClass", "############count:" + count);
                        if (count == 1) {
                            if (type == 1) {
                                parseMData(doc);
                            } else if (type == 2) {
                                parseLastData(doc);
                            }
                            tanCompleteListener.onTanLoadMainDataListener();
                        } else if (count >= 20) {
                            if (count % 10 == 0) {
                                int step = Integer.valueOf(count.toString()) / 10 - 2;
                                int yaCount = 0;
                                int ouCount = 0;
                                int raCount = 0;
                                if (mDataList.size() % 10 == 0) {
                                    yaCount = mDataList.size() / 10;
                                } else {
                                    yaCount = mDataList.size() / 10 + 1;
                                }
                                if (mDataList.size() % 10 == 0) {
                                    ouCount = mDataList.size() / 10;
                                } else {
                                    ouCount = mDataList.size() / 10 + 1;
                                }
                                if (mDataList.size() % 5 == 0) {
                                    raCount = mDataList.size() / 5;
                                } else {
                                    raCount = mDataList.size() / 5 + 1;
                                }
                                if (step < yaCount) {
                                    parseYaData(step, 10);
                                    tanCompleteListener.onTanLoadYaDataListener();
                                } else if (step < yaCount + ouCount) {
                                    parseOuData(step - yaCount, 10);
                                    tanCompleteListener.onTanLoadOuDataListener();
                                } else if (step < yaCount + ouCount + raCount) {
                                    parseRData(step - yaCount - ouCount, 5);
                                    tanCompleteListener.onTanLoadRaDataListener();
                                } else if (!isFinish) {
                                    isFinish = true;
                                    Log.i("MClass", "解析完成.......");
                                    tanCompleteListener.onTanCompleteListener(mDataList);
                                }
                            }
                        }
                    }
                });
    }

    private static void parseRData(int step, int count) {
        Log.i("MClass", "开始解析欧指数据，开始位置:" + (step) * count);
        for (int i = Math.max(step * count, 0); i < Math.min(mDataList.size(), (step + 1) * count); i ++) {
            Log.i("MClass", "开始解析第" + (i + 1) + "场近期比赛数据");
            final MainBean mainBean = mDataList.get(i);
            Log.i("MClass", "近期比赛：" + mDataList.get(i).getAUrl());
            apiClient.getNetClient().doGetRequestHtml(mDataList.get(i).getAUrl(), new HashMap<String, String>())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            mainBean.setdList(parseDList(s));
                            mainBean.setzList(parseZList(s));
                            mainBean.setkList(parseKList(s));
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("MClass", "error......" + throwable.getMessage());
                        }
                    });
        }
    }

    private static void parseOuData(int step, int count) {
        Log.i("MClass", "开始解析欧指数据，开始位置:" + (step) * count);
        for (int i = Math.max(step * count, 0); i < Math.min(mDataList.size(), (step + 1) * count); i ++) {
            Log.i("MClass", "开始解析第" + (i + 1) + "场比赛欧指数据");
            final MainBean mainBean = mDataList.get(i);
            apiClient.getNetClient().doGetRequestHtml(mainBean.getOuUrl(), new HashMap<String, String>())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            String[] result = s.split("\n");
                            String ouData = result[28];
                            if (ouData.contains("\"") && ouData.contains("\"")) {
                                String ouData1 = ouData.substring(ouData.indexOf("\"") + 1, ouData.lastIndexOf("\""));
                                String[] ouData2 = ouData1.split("\",\"");
                                List<OBean> oList = new ArrayList();
                                for (int i = 0; i < ouData2.length; i ++) {
                                    String[] ouData3 = ouData2[i].split("\\|");
                                    String company = ouData3[21];
                                    String startS = ouData3[3];
                                    String startP = ouData3[4];
                                    String startF = ouData3[5];
                                    String endS = ouData3[10];
                                    String endP = ouData3[11];
                                    String endF = ouData3[12];
                                    OBean oBean = new OBean();
                                    oBean.setCompany(company);
                                    oBean.setEndF(endF);
                                    oBean.setEndP(endP);
                                    oBean.setEndS(endS);
                                    oBean.setStartF(startF);
                                    oBean.setStartP(startP);
                                    oBean.setStartS(startS);
                                    oList.add(oBean);
                                }

                                mainBean.setoList(oList);
                            }
                        }
                    });
        }
    }

    private static void parseYaData(int step, int count) {
        try {
            Log.i("MClass", "开始解析亚盘数据，开始位置:" + (step) * count);
            for (int j = Math.max(step * count, 0); j < Math.min(mDataList.size(), (step + 1) * count); j++) {
                Log.i("MClass", "开始解析第" + (j + 1) + "场比赛亚盘信息");
                Document doc = Jsoup.connect(mDataList.get(j).getYaUrl()).get();
                final Element tableElement = doc.getElementById("odds");
                Elements trElement = tableElement.getElementsByTag("tr");
                List<YBean> mList = new ArrayList();
                for (int i = 0; i < trElement.size(); i++) {
                    if (trElement.get(i).attr("class") == "" &&
                            trElement.get(i).child(2).attr("title") != "") {
                        YBean yBean = new YBean();
                        yBean.setCompany(trElement.get(i).child(0).text().trim());
                        yBean.setStartTime(trElement.get(i).child(2).attr("title"));
                        yBean.setStartZRate(trElement.get(i).child(2).text());
                        yBean.setStartPan(ParserUtil.changePan(trElement.get(i).child(3).text().trim()));
                        yBean.setStartKRate(trElement.get(i).child(4).text());
                        yBean.setEndZRate(trElement.get(i).child(5).text());
                        yBean.setEndPan(ParserUtil.changePan(trElement.get(i).child(6).text().trim()));
                        yBean.setEndKRate(trElement.get(i).child(7).text());
                        mList.add(yBean);
                    }
                }
                mDataList.get(j).setyList(mList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseMData(Document doc) {
        mDataList.clear();
        final Elements elements = doc.body().getElementsByAttribute("index");
        for (int i = 0; i < Math.min(elements.size(), 10000); i ++) {
            // 亚盘欧指数据不为空，状态为空
            if (!TextUtils.isEmpty(elements.get(i).child(8).text()) && TextUtils.isEmpty(elements.get(i).child(3).text())) {
                Log.i("MClass", "开始解析第" + (i + 1) + "条数据..........");
                MainBean mainBean = new MainBean();
                String ids = elements.get(i).attr("id").substring(elements.get(i).attr("id").indexOf("_") + 1);
                String liansai = elements.get(i).child(1).text();
                String time = elements.get(i).child(2).text();
                String status = elements.get(i).child(3).text();
                if (status.equals("")) {
                    status = "未开始";
                }
                String zhu = elements.get(i).child(4).child(3).text();
                String bifen = elements.get(i).child(5).text();
                if (bifen.trim().equals("-")) {
                    bifen = "0:0";
                }
                String ke = elements.get(i).child(6).child(0).text();
                mainBean.setId(ids);
                mainBean.setLiansai(liansai);
                mainBean.setTime(time);
                mainBean.setStatus(status);
                mainBean.setZhu(zhu);
                mainBean.setBifen(bifen);
                mainBean.setKe(ke);
                mDataList.add(mainBean);
            } else {
                Log.i("MClass", "开始解析第" + (i + 1) + "条数据，数据不全舍弃.......");
            }
        }
        Log.i("MClass", "有效比赛数据：" + mDataList.size() + "场");
        tanCompleteListener.onTanLoadMainDataCompleteListener(mDataList.size());
    }

    /**
     * 解析比赛数据
     */
    public static void parseLastData(Document doc) {
        mDataList.clear();
        final Elements elements = doc.body().getElementsByAttribute("infoid");
        for (int i = 0; i < Math.min(elements.size(), 10000); i ++) {
            // 亚盘欧指数据不为空，状态为空
            if (!TextUtils.isEmpty(elements.get(i).child(7).text())) {
                Log.i("MClass", "开始解析第" + (i + 1) + "条数据..........");
                MainBean mainBean = new MainBean();
                String temp = elements.get(i).child(4).attr("onClick");
                String ids = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));
                String liansai = elements.get(i).child(0).text();
                String time = elements.get(i).child(1).text();
                String status = elements.get(i).child(2).text();
                String zhu = elements.get(i).child(3).text();
                String bifen = elements.get(i).child(4).text();
                String ke = elements.get(i).child(5).text();
                mainBean.setId(ids);
                mainBean.setLiansai(liansai);
                mainBean.setTime(time);
                mainBean.setStatus(status);
                mainBean.setZhu(zhu);
                mainBean.setBifen(bifen);
                mainBean.setKe(ke);
                mDataList.add(mainBean);
            } else {
                Log.i("MClass", "开始解析第" + (i + 1) + "条数据，数据不全舍弃.......");
            }
        }
        Log.i("MClass", "有效比赛数据：" + mDataList.size() + "场");
    }

    public static List<NBean> parseZList(String s) {
        String subStr1 = s.substring(s.indexOf("h_data"));
        String subStr2 = subStr1.substring(9, subStr1.indexOf("]];"));
        String[] arrayStr1 = subStr2.split("],\\[");
        List<NBean> zList = new ArrayList();
        for (int i = 0; i < arrayStr1.length; i ++) {
            String subStr3 = arrayStr1[i];
            String[] arrayStr2 = subStr3.split(",");
            NBean nBean = new NBean();
            nBean.setDate(arrayStr2[0].substring(1, arrayStr2[0].length() - 1));
            nBean.setKePoint(arrayStr2[9]);
            nBean.setLiansai(arrayStr2[2].substring(1, arrayStr2[2].length() - 1));
            nBean.setZhuPoint(arrayStr2[8]);
            nBean.setIds(arrayStr2[15]);
            try {
                if (s.contains(nBean.getIds() + ",8,") && s.contains(nBean.getIds() + ",12,")) {
                    int startIndex = s.indexOf(nBean.getIds() + ",8,");
                    int endIndex = s.indexOf(nBean.getIds() + ",12,");
                    String pan = s.substring(startIndex, endIndex);
                    String[] splits = pan.split(",");
                    if (splits != null && splits.length > 8) {
                        nBean.setzRate(splits[5].substring(1, splits[5].length() - 1));
                        nBean.setyPan(splits[6].substring(1, splits[6].length() - 1));
                        nBean.setkRate(splits[7].substring(1, splits[7].length() - 1));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Document doc = Jsoup.parseBodyFragment(arrayStr2[5] + arrayStr2[7]);
            Elements elements = doc.getElementsByAttribute("title");
            if (elements.size() == 2) {
                nBean.setZhudui(elements.get(0).text());
                nBean.setKedui(elements.get(1).text());
            }
            if (!TextUtils.isEmpty(nBean.getZhudui()) && !TextUtils.isEmpty(nBean.getKedui())) {
                zList.add(nBean);
            }
        }

        return zList;
    }

    public static List<NBean> parseKList(String s) {
        String subStr1 = s.substring(s.indexOf("a_data"));
        String subStr2 = subStr1.substring(11, subStr1.indexOf("]];"));
        String[] arrayStr1 = subStr2.split("],\\[");
        List<NBean> kList = new ArrayList();
        for (int i = 0; i < arrayStr1.length; i ++) {
            String subStr3 = arrayStr1[i];
            String[] arrayStr2 = subStr3.split(",");
            NBean nBean = new NBean();
            nBean.setDate(arrayStr2[0].substring(1, arrayStr2[0].length() - 1));
            nBean.setKePoint(arrayStr2[9]);
            nBean.setLiansai(arrayStr2[2].substring(1, arrayStr2[2].length() - 1));
            nBean.setZhuPoint(arrayStr2[8]);
            nBean.setIds(arrayStr2[15]);
            try {
                if (s.contains(nBean.getIds() + ",8,") && s.contains(nBean.getIds() + ",12,")) {
                    int startIndex = s.indexOf(nBean.getIds() + ",8,");
                    int endIndex = s.indexOf(nBean.getIds() + ",12,");
                    String pan = s.substring(startIndex, endIndex);
                    String[] splits = pan.split(",");
                    if (splits != null && splits.length > 8) {
                        nBean.setzRate(splits[5].substring(1, splits[5].length() - 1));
                        nBean.setyPan(splits[6].substring(1, splits[6].length() - 1));
                        nBean.setkRate(splits[7].substring(1, splits[7].length() - 1));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Document doc = Jsoup.parseBodyFragment(arrayStr2[5] + arrayStr2[7]);
            Elements elements = doc.getElementsByAttribute("title");
            if (elements.size() == 2) {
                nBean.setZhudui(elements.get(0).text());
                nBean.setKedui(elements.get(1).text());
            }
            if (!TextUtils.isEmpty(nBean.getZhudui()) && !TextUtils.isEmpty(nBean.getKedui())) {
                kList.add(nBean);
            }
        }

        return kList;
    }

    public static List<NBean> parseDList(String s) {
        if (s.contains("v_data = []")) {
            return new ArrayList<NBean>();
        }
        String subStr1 = s.substring(s.indexOf("v_data"));
        String subStr2 = subStr1.substring(11, subStr1.indexOf("]];"));
        String[] arrayStr1 = subStr2.split("],\\[");
        List<NBean> dList = new ArrayList();
        for (int i = 0; i < arrayStr1.length; i ++) {
            String subStr3 = arrayStr1[i];
            String[] arrayStr2 = subStr3.split(",");
            NBean nBean = new NBean();
            nBean.setDate(arrayStr2[0].substring(1, arrayStr2[0].length() - 1));
            nBean.setKePoint(arrayStr2[9]);
            nBean.setLiansai(arrayStr2[2].substring(1, arrayStr2[2].length() - 1));
            nBean.setZhuPoint(arrayStr2[8]);
            nBean.setIds(arrayStr2[15]);

            try {
                if (s.contains(nBean.getIds() + ",8,") && s.contains(nBean.getIds() + ",12,")) {
                    int startIndex = s.indexOf(nBean.getIds() + ",8,");
                    int endIndex = s.indexOf(nBean.getIds() + ",12,");
                    String pan = s.substring(startIndex, endIndex);
                    String[] splits = pan.split(",");
                    if (splits != null && splits.length > 8) {
                        nBean.setzRate(splits[5].substring(1, splits[5].length() - 1));
                        nBean.setyPan(splits[6].substring(1, splits[6].length() - 1));
                        nBean.setkRate(splits[7].substring(1, splits[7].length() - 1));
                    }
                }
                /*if (s.contains(nBean.getIds() + ",281,") && s.contains(nBean.getIds() + ",545,")) {
                    int startIndex = s.indexOf(nBean.getIds() + ",281,");
                    int endIndex = s.indexOf(nBean.getIds() + ",545,");
                    String pan = s.substring(startIndex, endIndex);
                    String[] splits = pan.split(",");
                    nBean.setOsRate(splits[5].substring(1, splits[5].length() - 1));
                    nBean.setOpRate(splits[6].substring(1, splits[6].length() - 1));
                    nBean.setOfRate(splits[7].substring(1, splits[7].length() - 1));
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }

            Document doc = Jsoup.parseBodyFragment(arrayStr2[5] + arrayStr2[7]);
            Elements elements = doc.getElementsByAttribute("title");
            if (elements.size() == 2) {
                nBean.setZhudui(elements.get(0).text());
                nBean.setKedui(elements.get(1).text());
            }
            if (!TextUtils.isEmpty(nBean.getZhudui()) && !TextUtils.isEmpty(nBean.getKedui())) {
                dList.add(nBean);
            }
        }

        return dList;
    }

    public static List<RBean> parseResult(Document doc) {
        Elements elements = doc.body().child(0).getElementsByAttribute("infoid");
        List mList = new ArrayList<RBean>();
        for (int i = 0; i < elements.size(); i ++) {
            RBean rbean = new RBean();
            rbean.setLiansai(elements.get(i).child(0).text());
            rbean.setDate(elements.get(i).child(1).text());
            rbean.setStatus(elements.get(i).child(2).text());
            rbean.setZhudui(elements.get(i).child(3).text());
            rbean.setPoints(elements.get(i).child(4).text());
            rbean.setKedui(elements.get(i).child(5).text());
            mList.add(rbean);
        }

        return mList;
    }
}
