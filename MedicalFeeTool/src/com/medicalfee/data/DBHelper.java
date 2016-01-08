package com.medicalfee.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.medicalfee.javabean.MedicalFeeBean;

public class DBHelper extends SQLiteOpenHelper {
	public static int version = 1;
	String userTableName = "lxl";
	String CREATE_TABLE = "create table " + userTableName + " (name text,age numeric,data numeric);";

	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	/**
	 * 1、在第一次打开数据库的时候才会走 2、在清除数据之后再次运行-->打开数据库，这个方法会走 3、没有清除数据，不会走这个方法
	 * 4、数据库升级的时候这个方法不会走
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	/**
	 * 1、第一次创建数据库的时候，这个方法不会走 2、清除数据后再次运行(相当于第一次创建)这个方法不会走
	 * 3、数据库已经存在，而且版本升高的时候，这个方法才会调用
	 */

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 删除用户表
		db.execSQL("drop table if exists " + userTableName);
		// 创建表
		onCreate(db);
	}

	public void InsertData(String a, String b, String c) {
		String[] Data = { a, b, c };
		String INSERT_SQL = "insert into " + userTableName + " values(?,?,?);";
		SQLiteDatabase write = getWritableDatabase();
		write.execSQL(INSERT_SQL, Data);
		write.close();
	}

	public ArrayList<MedicalFeeBean> QueryData() {
		String SQL = "select * from " + userTableName + ";";
		ArrayList<MedicalFeeBean> al = new ArrayList<MedicalFeeBean>();
		SQLiteDatabase read = getReadableDatabase();
		Cursor cursor = read.rawQuery(SQL, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				cursor.getColumnNames();
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String age = cursor.getString(cursor.getColumnIndex("age"));
				String data = cursor.getString(cursor.getColumnIndex("data"));
				// MedicalFeeModel bean = new MedicalFeeModel(name, age, data);
				// al.add(bean);
			}
			cursor.close();
		}
		read.close();
		return al;
	}

	public void DeleteData(String a, String b) {
		String deleteSql = "delete from " + userTableName + " where " + a + "='" + b + "';";
		SQLiteDatabase write = getWritableDatabase();
		write.execSQL(deleteSql);
		write.close();
	}

	public void Update(String a, String b, String c, String d) {
		String updateSql = "update " + userTableName + " set " + a + " =" + "'" + b + "'" + " where " + c + "='" + d + "';";
		// String updateSql_1 = "update " + userTableName + " set " + b + " =" +
		// "'" + d + "'" + " where " + b + "='" + c + "';";
		SQLiteDatabase write = getWritableDatabase();
		write.execSQL(updateSql);
		// 关闭
		write.close();
	}

}
