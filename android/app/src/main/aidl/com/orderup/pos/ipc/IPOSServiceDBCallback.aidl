package com.orderup.pos.ipc;

import com.orderup.pos.ipc.AlterTableData;
import com.orderup.pos.ipc.UpdateTableData;
import com.orderup.pos.ipc.POSState;
import com.orderup.pos.ipc.POSCursor;
import android.content.ContentValues;

interface IPOSServiceDBCallback {

    AlterTableData[] onDBAlterTableCallback();

    ContentValues onDBLoadData(String table, in POSCursor cursor);

    void onDBStartCallback(in POSState state);

    void onBlockingDBLoadedCallback(in POSState state);

    void onDBEndCallback(in POSState state);

    void onBlockingDBEndCallback(in POSState state);

    void onDBUpdateCallback(in UpdateTableData[] data);

}