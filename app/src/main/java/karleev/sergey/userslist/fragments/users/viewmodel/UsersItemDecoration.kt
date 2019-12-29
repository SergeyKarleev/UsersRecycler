package karleev.sergey.userslist.fragments.users.viewmodel

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


/**
 * Created by Sergey Karleev on 29.12.2019.
 */
class UsersItemDecoration(context: Context) : ItemDecoration() {
    private val mPadding: Int

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
//        if (itemPosition == 0) {
//            outRect.top = mPadding
//        }
//        val adapter = parent.adapter
//        if (adapter != null && itemPosition == adapter.itemCount - 1) {
//            outRect.bottom = mPadding
//        }
//        outRect.left = mPadding
    }


    companion object {
        private const val PADDING_IN_DIPS = 50
    }

    init {
        val metrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        mPadding = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            PADDING_IN_DIPS.toFloat(),
            metrics
        ).toInt()
    }
}