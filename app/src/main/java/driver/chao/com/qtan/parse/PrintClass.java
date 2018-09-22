package driver.chao.com.qtan.parse;

import java.util.ArrayList;
import java.util.List;

import driver.chao.com.qtan.bean.MainBean;

/**
 * Created by aaron on 2018/9/13.
 * 解析数据
 */
public class PrintClass {

    /**
     * 1.44盘口比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parse144(List<MainBean> mDataList) {
        ArrayList<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            for (int j = 0; j < mDataList.get(i).getoList().size(); j ++) {
                if (mDataList.get(i).getoList().get(j).company.contains("威廉希尔")) {
                    if (mDataList.get(i).getoList().get(j).endS.equals("1.44")
                            || mDataList.get(i).getoList().get(j).endF.equals("1.44")) {
                        mList.add(mDataList.get(i));
                    }
                }
            }
        }

        return mList;
    }

    /**
     * 1.65盘口比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parse165(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            for (int j = 0; j < mDataList.get(i).getoList().size(); j ++) {
                if (mDataList.get(i).getoList().get(j).company.contains("威廉希尔")) {
                    if (mDataList.get(i).getoList().get(j).startS.equals("1.65")
                            || mDataList.get(i).getoList().get(j).startF.equals("1.65")
                            || mDataList.get(i).getoList().get(j).endS.equals("1.65")
                            || mDataList.get(i).getoList().get(j).endF.equals("1.65")) {
                        mList.add(mDataList.get(i));
                    }
                }
            }
        }

        return mList;
    }

    /**
     * 解析变盘比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseCOver(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (Math.abs(startPan - endPan) >= 0.25 && Math.abs(endPan) < 1.75) {
                        count = count + 1;
                    }
                }
            }
            if (count >= 3) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 解析下盘比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseDown(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            int totalCount = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (endPan > 0 && startPan - endPan == 0f && Float.valueOf(mDataList.get(i).getyList().get(j).startZRate) <= 0.86 &&
                            Float.valueOf(mDataList.get(i).getyList().get(j).endZRate) - Float.valueOf(mDataList.get(i).getyList().get(j).startZRate) > 0) {
                        count = count + 1;
                    } else if (endPan < 0 && startPan - endPan == 0f && Float.valueOf(mDataList.get(i).getyList().get(j).startKRate) <= 0.86 &&
                            Float.valueOf(mDataList.get(i).getyList().get(j).endKRate) - Float.valueOf(mDataList.get(i).getyList().get(j).startKRate) > 0) {
                        count = count + 1;
                    }
                    totalCount = totalCount + 1;
                }

                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (endPan > 0 && startPan - endPan == 0f && Float.valueOf(mDataList.get(i).getyList().get(j).startZRate) >= 1 &&
                            Float.valueOf(mDataList.get(i).getyList().get(j).startZRate) - Float.valueOf(mDataList.get(i).getyList().get(j).endZRate) > 0) {
                        count = count + 1;
                    } else if (endPan < 0 && startPan - endPan == 0f && Float.valueOf(mDataList.get(i).getyList().get(j).startKRate) >= 1 &&
                            Float.valueOf(mDataList.get(i).getyList().get(j).startKRate) - Float.valueOf(mDataList.get(i).getyList().get(j).endKRate) > 0) {
                        count = count + 1;
                    }
                }
            }

            if (count >= totalCount / 2) {
                mList.add(mDataList.get(i));
            }
        }

        List<MainBean> resultList = new ArrayList();
        for (int i = 0; i < mList.size(); i ++) {
            if (mList.get(i).yList.size() <= 0 || mList.get(i).kList.size() <= 0 || mList.get(i).zList.size() <= 0) {
                continue;
            }
            float endPan = Float.valueOf(mList.get(i).yList.get(0).endPan);
            if (endPan > 0) {
                // 下盘客队->最近一场红了
                // 上盘主队->最近一场黑了
                String kedui = mList.get(i).getKe();
                if (kedui.trim().contains(mList.get(i).kList.get(0).zhudui.trim()) || mList.get(i).kList.get(0).zhudui.trim().contains(kedui.trim())) {
                    if (Float.valueOf(mList.get(i).kList.get(0).zhuPoint) > Float.valueOf(mList.get(i).kList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                } else if (kedui.trim().contains(mList.get(i).kList.get(0).kedui.trim()) || mList.get(i).kList.get(0).kedui.trim().contains(kedui.trim())) {
                    if (Float.valueOf(mList.get(i).kList.get(0).zhuPoint) < Float.valueOf(mList.get(i).kList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                }

                String zhudui = mList.get(i).getZhu();
                if (zhudui.trim().contains(mList.get(i).zList.get(0).zhudui.trim()) || mList.get(i).zList.get(0).zhudui.trim().contains(zhudui.trim())) {
                    if (Float.valueOf(mList.get(i).zList.get(0).zhuPoint) < Float.valueOf(mList.get(i).zList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                } else if (zhudui.trim().contains(mList.get(i).zList.get(0).kedui.trim()) || mList.get(i).zList.get(0).kedui.trim().contains(zhudui.trim())) {
                    if (Float.valueOf(mList.get(i).zList.get(0).zhuPoint) > Float.valueOf(mList.get(i).zList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                }
            } else if (endPan < 0) {
                // 下盘主队->最近一场红了
                // 上盘客队->最近一场黑了
                String zhudui = mList.get(i).getZhu();
                if (zhudui.trim().contains(mList.get(i).zList.get(0).zhudui.trim()) || mList.get(i).zList.get(0).zhudui.trim().contains(zhudui.trim())) {
                    if (Float.valueOf(mList.get(i).zList.get(0).zhuPoint) > Float.valueOf(mList.get(i).zList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                } else if (zhudui.trim().contains(mList.get(i).zList.get(0).kedui.trim()) || mList.get(i).zList.get(0).kedui.trim().contains(zhudui.trim())) {
                    if (Float.valueOf(mList.get(i).zList.get(0).zhuPoint) < Float.valueOf(mList.get(i).zList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                }

                String kedui = mList.get(i).getZhu();
                if (kedui.trim().contains(mList.get(i).kList.get(0).zhudui.trim()) || mList.get(i).kList.get(0).zhudui.trim().contains(kedui.trim())) {
                    if (Float.valueOf(mList.get(i).kList.get(0).zhuPoint) < Float.valueOf(mList.get(i).zList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                } else if (kedui.trim().contains(mList.get(i).kList.get(0).kedui.trim()) || mList.get(i).kList.get(0).kedui.trim().contains(kedui.trim())) {
                    if (Float.valueOf(mList.get(i).kList.get(0).zhuPoint) > Float.valueOf(mList.get(i).kList.get(0).kePoint)) {
                        resultList.add(mList.get(i));
                    }
                }
            }
        }

        return resultList;
    }

    /**
     * 解析平手盘比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseZero(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (startPan - endPan == 0f && startPan == 0f) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 3) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 降盘降水比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseCut(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (endPan >= 0 && endPan <= 1 && startPan >= 0 && startPan <= 1) {
                        if (endPan - startPan < 0) {
                            float endRate = Float.valueOf(mDataList.get(i).getyList().get(j).endKRate);
                            float startRate = Float.valueOf(mDataList.get(i).getyList().get(j).startKRate);
                            if (endRate - startRate < 0) {
                                count = count + 1;
                            }
                        }
                    } else if (endPan <= 0 && endPan >= -1 && startPan <= 0 && startPan >= -1) {
                        if (endPan - startPan > 0) {
                            float endRate = Float.valueOf(mDataList.get(i).getyList().get(j).endZRate);
                            float startRate = Float.valueOf(mDataList.get(i).getyList().get(j).startZRate);
                            if (endRate - startRate < 0) {
                                count = count + 1;
                            }
                        }
                    }
                }
            }

            if (count >= 1) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 平手盘至->平半盘比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parse025(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (startPan == 0 && (endPan == -0.25 || endPan == 0.25)) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 2) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 深盘变盘比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseDeep(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (Math.abs(startPan) >= 1 && Math.abs(endPan) - Math.abs(startPan) >= 0.25) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 2) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 075-05比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parse075To05(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (startPan == 0.75 && endPan == 0.5) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 2) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 075-1比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parse075To1(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (startPan == 0.75 && endPan == 1) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 2) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 1-125比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parse1To125(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (startPan == 1 && endPan == 1.25) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 2) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 连续变盘比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseOverMore(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            int count = 0;
            for (int j = 0; j < mDataList.get(i).getyList().size(); j ++) {
                if (isCompany(mDataList.get(i).getyList().get(j).company)) {
                    float endPan = Float.valueOf(mDataList.get(i).getyList().get(j).endPan);
                    float startPan = Float.valueOf(mDataList.get(i).getyList().get(j).startPan);
                    if (Math.abs(startPan - endPan) >= 0.5) {
                        count = count + 1;
                    }
                }
            }

            if (count >= 2) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }

    /**
     * 关注比赛
     * @param mDataList
     * @return
     */
    public static List<MainBean> parseLike(List<MainBean> mDataList) {
        List<MainBean> mList = new ArrayList();
        for (int i = 0; i < mDataList.size(); i ++) {
            if (mDataList.get(i).getLike()) {
                mList.add(mDataList.get(i));
            }
        }

        return mList;
    }


    private static boolean isCompany(String company) {
        return company.contains("365") ||
                company.contains("易胜博") ||
                company.contains("Crown") ||
                company.contains("威廉") ||
                company.contains("韦德") ||
                company.contains("澳门") ||
                company.contains("金宝博") ||
                company.contains("盈禾") ||
                company.contains("立博");
    }
}
