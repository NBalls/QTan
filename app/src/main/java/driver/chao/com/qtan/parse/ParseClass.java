package driver.chao.com.qtan.parse;

import android.text.TextUtils;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import driver.chao.com.qtan.APIClient;
import driver.chao.com.qtan.bean.MainBean;
import driver.chao.com.qtan.bean.NBean;
import driver.chao.com.qtan.bean.OBean;
import driver.chao.com.qtan.bean.RBean;
import driver.chao.com.qtan.bean.YBean;
import driver.chao.com.qtan.util.ParserUtil;
import driver.chao.com.qtan.util.TanCompleteListener;
import driver.chao.com.qtan.util.TanListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by aaron on 2018/7/23.
 * 解析数据
 */
public class ParseClass {
    public static TanCompleteListener tanCompleteListener = null;
    private static APIClient apiClient = new APIClient();
    private static ArrayList mDataList = new ArrayList();
    private static boolean isFinish = false;
    private static int count = 0;

    /**
     * 解析比赛数据
     */
    public static void parseMainData(Document doc) {
        count = 0;
        isFinish = false;
        mDataList.clear();
        final Elements elements = doc.body().getElementsByAttribute("index");
        final TanListener tanListener = new TanListener() {
            @Override
            public void onTanListener() {
                if (mDataList.size() >= count - 3 && isFinish) {
                    if (tanCompleteListener != null) {
                        tanCompleteListener.onTanCompleteListener(mDataList);
                    }
                }
            }
        };
        for (int i = 3; i < Math.min(elements.size(), 200); i ++) {
            // 亚盘欧指数据不为空，状态为空
            if (!TextUtils.isEmpty(elements.get(i).child(8).text())
                    && TextUtils.isEmpty(elements.get(i).child(3).text())) {
                count = count + 1;
                Log.i("MClass", "开始解析第" + (i + 1) + "条数据..........");
                MainBean mainBean = new MainBean();
                String ids = elements.get(i).attr("id")
                        .substring(elements.get(i).attr("id").indexOf("_") + 1);
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
                parseYOData(mainBean, tanListener);
            } else {
                Log.i("MClass", "开始解析第" + (i + 1) + "条数据，数据不全舍弃.......");
            }
            if (i >= Math.min(elements.size(), 200) - 1) {
                isFinish = true;
            }
        }
    }

    /**
     * 解析亚盘数据
     * @param mainBean
     */
    public static void parseYOData(final MainBean mainBean, final TanListener tanListener) {
        try {
            Document doc = Jsoup.connect(mainBean.getYaUrl()).get();
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
                    yBean.setStartPan(ParserUtil.changePan(trElement.get(i).child(3).text()));
                    yBean.setStartKRate(trElement.get(i).child(4).text());
                    yBean.setEndZRate(trElement.get(i).child(5).text());
                    yBean.setEndPan(ParserUtil.changePan(trElement.get(i).child(6).text()));
                    yBean.setEndKRate(trElement.get(i).child(7).text());
                    mList.add(yBean);
                }
            }
            mainBean.setyList(mList);

            apiClient.getNetClient().doGetRequestHtml(mainBean.getOuUrl(), new HashMap<String, String>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
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
                                mDataList.add(mainBean);
                                tanListener.onTanListener();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Observable<String> parseRData(final MainBean mainBean) {
        return apiClient.getNetClient().doGetRequestHtml(mainBean.getAUrl(), new HashMap<String, String>());
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
            Document doc = Jsoup.parseBodyFragment(arrayStr2[5] + arrayStr2[7]);
            Elements elements = doc.getElementsByAttribute("title");
            if (elements.size() == 2) {
                nBean.setZhudui(elements.get(0).text());
                nBean.setKedui(elements.get(1).text());
            }
            if (!TextUtils.isEmpty(nBean.getZhudui()) && !TextUtils.isEmpty(nBean.getKedui())) {
                zList.add(nBean);
            }
            // Log.i("MClass", nBean.toString());
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
            Document doc = Jsoup.parseBodyFragment(arrayStr2[5] + arrayStr2[7]);
            Elements elements = doc.getElementsByAttribute("title");
            if (elements.size() == 2) {
                nBean.setZhudui(elements.get(0).text());
                nBean.setKedui(elements.get(1).text());
            }
            if (!TextUtils.isEmpty(nBean.getZhudui()) && !TextUtils.isEmpty(nBean.getKedui())) {
                kList.add(nBean);
            }
            Log.i("MClass", nBean.toString());
        }

        return kList;
    }

    public static List<NBean> parseDList(String s) {
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
            Document doc = Jsoup.parseBodyFragment(arrayStr2[5] + arrayStr2[7]);
            Elements elements = doc.getElementsByAttribute("title");
            if (elements.size() == 2) {
                nBean.setZhudui(elements.get(0).text());
                nBean.setKedui(elements.get(1).text());
            }
            if (!TextUtils.isEmpty(nBean.getZhudui()) && !TextUtils.isEmpty(nBean.getKedui())) {
                dList.add(nBean);
            }
            // Log.i("MClass", nBean.toString());
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
