package com.example.cancerhelpmate.common;

import android.content.Context;

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    Context getContext();
}

