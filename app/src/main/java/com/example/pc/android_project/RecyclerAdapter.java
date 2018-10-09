package com.example.pc.android_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/* RecyclerAdapter 클래스
   - RecyclerView.Adapter를 상속한 RecyclerAdapter 클래스 선언
   - RecyclerView.Adapter를 상속받으면 ViewHolder를 반드시 정의해야 함
   - RecyclerView에서는 ViewHolder 패턴을 강제화하여 구현
 */
public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Drama_item> items;//어댑터에서 사용할 데이터(Item) 정의
    int item_layout;

    //RecyclerAdapter 생성자
    public RecyclerAdapter(Context context, List<Drama_item> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    /* 새로운 ViewHolder 생성 - LayoutManager에 의해 호출됨
       - RecyclerView는 ViewHolder을 재사용할 수 있도록 설계되어 있으므로, 최초 한번만 호출됨
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //item_cardview inflate
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.drama_card,null);
        //ViewHolder 생성하고, 이를 반환(onBindViewHolder()의 매개변수로 전달되어 재사용됨)
        return new ViewHolder(view);
    }

    /* 아이템의 뷰에 데이터 설정 - LayoutManager에 의해 호출됨
       - 해당 holder의 View 에 데이터 설정
       - 아이템의 크기만큼 반복 호출되며, 매개변수로 전달된 ViewHolder를 통해 필요한 뷰(참조해 놓은)를 재사용
    */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // 데이터 집합(items)에서 해당 요소를 가져옴
        final Drama_item item = items.get(position);

        //item에서 image를 가져와 설정
        Drawable image = context.getResources().getDrawable(item.getImage());

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            holder.image.setBackground(image);
        } else {
            holder.image.setBackgroundDrawable(image);
        }

        //item에서 title를 가져와 설정
        holder.title.setText(item.getTitle());

        /* 이벤트 처리
           - recyclerView의 경우 ListView에 있었던 OnitemClickListner가 존재하지 않으므로
             같은 형식으로 쓰고 싶다면 아이템의 전체를 아우르는 뷰에 OnClickListener를 걸어준다
         */
        holder.cardview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), drama_control.class);
                Log.v("id",position+"");
                intent.putExtra("id",position);
                v.getContext().startActivity(intent);
            }
        });
    }

    //Item의 사이즈 리턴
    @Override
    public int getItemCount()
    {
        return this.items.size();
    }

    /* 커스텀 ViewHolder 정의
       - item_cardview.xml에 정의한 뷰들을 참조(바인딩)
       - 각 데이터 항목에 대한 뷰의 참조를 제공
       - findViewById 이슈 해결
   */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}
