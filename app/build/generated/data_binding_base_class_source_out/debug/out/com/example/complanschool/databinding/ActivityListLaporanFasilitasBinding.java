// Generated by view binder compiler. Do not edit!
package com.example.complanschool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.complanschool.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityListLaporanFasilitasBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView rcRiwayatLaporanFa;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView3;

  private ActivityListLaporanFasilitasBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView rcRiwayatLaporanFa, @NonNull TextView textView,
      @NonNull TextView textView3) {
    this.rootView = rootView;
    this.rcRiwayatLaporanFa = rcRiwayatLaporanFa;
    this.textView = textView;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityListLaporanFasilitasBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityListLaporanFasilitasBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_list_laporan_fasilitas, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityListLaporanFasilitasBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rc_RiwayatLaporanFa;
      RecyclerView rcRiwayatLaporanFa = ViewBindings.findChildViewById(rootView, id);
      if (rcRiwayatLaporanFa == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new ActivityListLaporanFasilitasBinding((ConstraintLayout) rootView,
          rcRiwayatLaporanFa, textView, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
