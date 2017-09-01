package app.com.ravi.callrecorder.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import app.com.ravi.callrecorder.BaseApp;
import app.com.ravi.callrecorder.util.CM;
import app.com.ravi.callrecorder.view.View_home;

public class tbl_notification {

    public static final String TableName = "notification";
    public static final String ID = "id";
    public static final String ISVIEWED = "isViewed";
    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String DATETIME = "datetime";
    public static final String CALLDURATION = "callduration";

    public static final String CALLTYPE = "callType";
    public static final String ISCLOUD = "isCloud";
    public static final String PERPIC = "perPic";
    public static final String TIME = "time";
    public static final String TEMPFILEPATH = "tempfilepath";
    public static final String isSave = "isSave";


//    public static ArrayList<DiscloserPojo> getAllData(String headerTitle) {
//
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        ArrayList<DiscloserPojo> arrModelList = null;
//        Cursor cursor = null;
//
//        headerTitle1 = "\"" + headerTitle + "\"";
//        String Query = "SELECT * FROM " + TableName + " where HeaderTitle =" + headerTitle1;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<DiscloserPojo>();
//            do {
//                DiscloserPojo model = new DiscloserPojo();
//                model.setPdfId(cursor.getString(cursor.getColumnIndex(PDFID)));
//                model.setTitle(cursor.getString(cursor.getColumnIndex(PDFNAME)));
//                model.setDownload(cursor.getString(cursor.getColumnIndex(PDFPATH)));
//                model.setIsDownload(cursor.getString(cursor.getColumnIndex(ISDOWNLOAD)));
//                model.setIsViewed(cursor.getString(cursor.getColumnIndex(ISVIEWED)));
//                model.setHeaderTitle(cursor.getString(cursor.getColumnIndex(HEADERTITLE)));
//                model.setDownloadStatus(cursor.getString(cursor.getColumnIndex(DOWNLOADSTATUS)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }


//    public static ArrayList<DiscloserPojo> getDistintTitleData() {
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        ArrayList<DiscloserPojo> arrModelList = null;
//        Cursor cursor = null;
//        String Query = "SELECT DISTINCT " + HEADERTITLE + "  FROM " + TableName;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<DiscloserPojo>();
//            do {
//                DiscloserPojo model = new DiscloserPojo();
//                model.setHeaderTitle((cursor.getString(cursor.getColumnIndex(HEADERTITLE))));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }

    public static ArrayList<NotiModel> getSortDateSave() {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        ArrayList<NotiModel> arrModelList = null;
        Cursor cursor = null;

        String key = "\"" + "true" + "\"";
        String Query = "SELECT DISTINCT DATETIME FROM notification where isSave =" + key;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<NotiModel>();
            do {
                NotiModel model = new NotiModel();
                model.setDatetime(cursor.getString(cursor.getColumnIndex(DATETIME)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }

    public static ArrayList<NotiModel> getSortDate() {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        ArrayList<NotiModel> arrModelList = null;
        Cursor cursor = null;
        String key = "0";

        String Query = "SELECT DISTINCT DATETIME FROM notification";
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<NotiModel>();
            do {
                NotiModel model = new NotiModel();
                model.setDatetime(cursor.getString(cursor.getColumnIndex(DATETIME)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


    public static ArrayList<NotiModel> getAllDataSave(String datetime) {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        ArrayList<NotiModel> arrModelList = null;
        Cursor cursor = null;
        String key = "\"" + datetime + "\"";
        String key1 = "\"" + "true" + "\"";
        String Query = "SELECT * FROM notification WHERE " + DATETIME + " = " + key + "and isSave =" + key1;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<NotiModel>();
            do {
                NotiModel model = new NotiModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                model.setIsViewed(cursor.getString(cursor.getColumnIndex(ISVIEWED)));
                model.setNumber(cursor.getString(cursor.getColumnIndex(NUMBER)));
                model.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                model.setCallDuration(cursor.getString(cursor.getColumnIndex(CALLDURATION)));
                model.setDatetime(cursor.getString(cursor.getColumnIndex(DATETIME)));
                model.setCallType(cursor.getString(cursor.getColumnIndex(CALLTYPE)));
                model.setIsCloud(cursor.getString(cursor.getColumnIndex(ISCLOUD)));
                model.setPerPic(cursor.getString(cursor.getColumnIndex(PERPIC)));
                model.setTime(cursor.getString(cursor.getColumnIndex(TIME)));
                model.setTempFile(cursor.getString(cursor.getColumnIndex(TEMPFILEPATH)));
                model.setIsSave(cursor.getString(cursor.getColumnIndex(isSave)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }

    public static ArrayList<NotiModel> getAllData(String datetime) {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        ArrayList<NotiModel> arrModelList = null;
        Cursor cursor = null;
        String key = "\"" + datetime + "\"";
        // headerTitle1 = "\"" + name + "\"";
        //+ " WHERE " + ISVIEWED + " = " + key
        // Cursor c = Db1.rawQuery("SELECT * FROM notification", null);
        String Query = "SELECT * FROM notification WHERE " + DATETIME + " = " + key;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<NotiModel>();
            do {
                NotiModel model = new NotiModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                model.setIsViewed(cursor.getString(cursor.getColumnIndex(ISVIEWED)));
                model.setNumber(cursor.getString(cursor.getColumnIndex(NUMBER)));
                model.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                model.setCallDuration(cursor.getString(cursor.getColumnIndex(CALLDURATION)));
                model.setDatetime(cursor.getString(cursor.getColumnIndex(DATETIME)));
                model.setCallType(cursor.getString(cursor.getColumnIndex(CALLTYPE)));
                model.setIsCloud(cursor.getString(cursor.getColumnIndex(ISCLOUD)));
                model.setPerPic(cursor.getString(cursor.getColumnIndex(PERPIC)));
                model.setTime(cursor.getString(cursor.getColumnIndex(TIME)));
                model.setTempFile(cursor.getString(cursor.getColumnIndex(TEMPFILEPATH)));
                model.setIsSave(cursor.getString(cursor.getColumnIndex(isSave)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


//    public static ArrayList<PojoItemDetail> getSelectedIdRecord(String pdfId) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItemDetail> arrModelList = null;
//        Cursor cursor = null;
//        //String Query = "Select * from " + TableName;
//        String Query = "Select * from " + TableName + " where " + NMAPPERID + " = " + pdfId;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItemDetail>();
//            do {
//                PojoItemDetail model = new PojoItemDetail();
//                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
//                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
//                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
//                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
//                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
//                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
//                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
//                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
//                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
//                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
//                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }


//    public static ArrayList<PojoItemDetail> getSelectedate(String name) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItemDetail> arrModelList = null;
//        Cursor cursor = null;
//        //String Query = "Select * from " + TableName;
//        headerTitle1 = "\"" + name + "\"";
//        String Query = "Select * from " + TableName + " where " + HEADER + " = " + headerTitle1 + " and isChecked = " + "1";
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItemDetail>();
//            do {
//                PojoItemDetail model = new PojoItemDetail();
//                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
//                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
//                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
//                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
//                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
//                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
//                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
//                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
//                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
//                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
//                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }
    /*
    * get single record
    * */


//    public static Model_ClubDetails SelectSingleRecord() {
//        SQLiteDatabase sqldb = KarnavatiApp.sqLiteDatabase;
//        Model_ClubDetails model = null;
//        Cursor cursor = null;
//        String Query = "Select * from " + TableName;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            model = new Model_ClubDetails();
//            model.Id = (cursor.getInt(cursor.getColumnIndex(ID)));
//            model.ClubName = (cursor.getString(cursor.getColumnIndex(CLUBNAME)));
//            model.ClubAddress = (cursor.getString(cursor.getColumnIndex(CLUBADDRESS)));
//            model.ClubPhone = (cursor.getString(cursor.getColumnIndex(CLUBPHONE)));
//            model.WebSite = (cursor.getString(cursor.getColumnIndex(WEBSITE)));
//            model.LatLong = (cursor.getString(cursor.getColumnIndex(LATLONG)));
//            model.EmailId = (cursor.getString(cursor.getColumnIndex(EMAILID)));
//            model.CreatedDate = (cursor.getString(cursor.getColumnIndex(CREATEDDATE)));
//            model.UpdatedDate = (cursor.getString(cursor.getColumnIndex(UPDATEDDATE)));
//            cursor.close();
//        }//end if(cursor!=null)
//        return model;
//    }


//    public static ArrayList<PojoItemDetail> getUniqueName() {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItemDetail> arrModelList = null;
//        Cursor cursor = null;
//        String Query = "SELECT DISTINCT " + HEADER + " from " + TableName + " Where isChecked = " + "1";
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItemDetail>();
//            do {
//                PojoItemDetail model = new PojoItemDetail();
//                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }


//    public static ArrayList<PojoItemDetail> getAllCheckedRecord() {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItemDetail> arrModelList = null;
//        Cursor cursor = null;
//        //String Query = "Select * from " + TableName;
//        String Query = "SELECT * FROM " + TableName + " where isChecked = " + "1";
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItemDetail>();
//            do {
//                PojoItemDetail model = new PojoItemDetail();
//                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
//                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
//                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
//                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
//                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
//                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
//                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
//                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
//                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
//                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
//                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }

//    public static String getAllCheckeditemRecord() {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItemDetail> arrModelList = null;
//        Cursor cursor = null;
//        String total = null;
//        String Query = "SELECT SUM(nPrice) FROM " + TableName + " where isChecked = " + "1";
//
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                total = String.valueOf(cursor.getInt(0));
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return total;
//    }

//    public static ArrayList<PojoItems> getAlldataNotReruirdwithAddOnRequird() {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItems> arrModelList = null;
//        Cursor cursor = null;
//        String bool = "\"" + "true" + "\"";
//        boolean b = false;
//        String Query = "Select * from " + TableName + " where " + ISREQUIRED + " =" + bool + " and " + ISCHECKED + "= 1";
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItems>();
//            do {
//                PojoItems model = new PojoItems();
//                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }


    public static void Insert(ArrayList<NotiModel> notiModels) {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        sqldb.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(NAME, notiModels.get(0).getName());
        values.put(NUMBER, notiModels.get(0).getNumber());
        values.put(DATETIME, notiModels.get(0).getDatetime());
        values.put(CALLDURATION, notiModels.get(0).getCallDuration());
        values.put(CALLTYPE, notiModels.get(0).getCallType());
        values.put(ISCLOUD, notiModels.get(0).getIsCloud());
        values.put(PERPIC, notiModels.get(0).getPerPic());
        values.put(TIME, notiModels.get(0).getTime());
        values.put(TEMPFILEPATH, notiModels.get(0).getTempFile());
        values.put(isSave, notiModels.get(0).getIsSave());


        if (CM.CheckIsDataAlreadyInDBorNot(TableName, NAME, notiModels.get(0).getName())) {
            try {
                sqldb.update(TableName, values, NAME + "=" + notiModels.get(0).getName(), null);
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            try {
                sqldb.insert(TableName, null, values);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        sqldb.setTransactionSuccessful();
        sqldb.endTransaction();
    }

    public static int deleteAll() {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        int row = sqldb.delete(TableName, null, null);
        return row;
    }

    public static boolean deleteItem(String name) {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        return sqldb.delete(TableName, ID + "=" + name, null) > 0;
    }

   /* public static void updateTbl(String key) {
        String isView = "1";
        String view = "0";
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        String selectQuery = "UPDATE " + TableName + " SET " + ISVIEWED + " = " + isView + " WHERE " + KEY + " = " + key;
        try {
            sqldb.execSQL(selectQuery);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }*/

    public static void updateAllTbl() {
        String isView = "1";
        String view = "0";
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        String selectQuery = "UPDATE " + TableName + " SET " + ISVIEWED + " = " + isView;
        try {
            sqldb.execSQL(selectQuery);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

//    public static void updateCheckBox(String isCheck, String nMapperDetailID, String nMapperID) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        String selectQuery = "UPDATE " + TableName + " SET " + ISCHECKED
//                + " = " + isCheck + " WHERE "
//                + NMAPPERID + " = " + nMapperID + " AND " + NMAPPERDETAILID + "=" + nMapperDetailID;
//        try {
//            sqldb.execSQL(selectQuery);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//        }
//
//
//    }
//
//    public static void setUncheckedRadioButton(String isCheck, String nMapperDetailID, String nMapperID) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        String selectQuery = "UPDATE " + TableName + " SET " + ISCHECKED
//                + " = " + 0 + " WHERE "
//                + NMAPPERID + " = " + nMapperID;
//        try {
//            sqldb.execSQL(selectQuery);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//        }
//
//
//    }


//    public static void updateRadioButton(String isCheck, String nMapperDetailID, String nMapperID) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        String selectQuery = "UPDATE " + TableName + " SET " + ISCHECKED
//                + " = " + isCheck + " WHERE "
//                + NMAPPERID + " = " + nMapperID + " AND " + NMAPPERDETAILID + "=" + nMapperDetailID;
//        try {
//            sqldb.execSQL(selectQuery);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//        }
//
//
//    }

    public static void updateIsSave(String id, String status) {
        SQLiteDatabase sqldb = BaseApp.sqLiteDatabase;
        String selectQuery = "UPDATE " + TableName + " SET " + isSave
                + " = '" + status + "' WHERE "
                + ID + " = " + id;
        try {
            sqldb.execSQL(selectQuery);

        } catch (Exception e) {

            e.getStackTrace();
        }


    }
//    public static ArrayList<PojoItemDetail> getAlldataUsingMapperId(String id) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItemDetail> arrModelList = null;
//        Cursor cursor = null;
//        //String Query = "Select * from " + TableName;
//        String Query = "Select * from " + TableName + " where " + NMAPPERID + " =" + id + " and isChecked = 1 ";
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItemDetail>();
//            do {
//                PojoItemDetail model = new PojoItemDetail();
//                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
//                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
//                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
//                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
//                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
//                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
//                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
//                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
//                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
//                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
//                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }
}