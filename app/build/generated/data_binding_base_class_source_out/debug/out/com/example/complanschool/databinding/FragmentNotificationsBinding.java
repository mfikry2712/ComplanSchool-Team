// Generated by view binder compiler. Do not edit!
package com.example.complanschool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.complanschool.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentNotificationsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnSignOut;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView15;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView tvDomicile;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPos;

  @NonNull
  public final TextView tvPrincipal;

  @NonNull
  public final TextView tvSchoolCode;

  @NonNull
  public final TextView tvSchoolName;

  private FragmentNotificationsBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnSignOut, @NonNull ImageView imageView2, @NonNull TextView textView10,
      @NonNull TextView textView12, @NonNull TextView textView15, @NonNull TextView textView2,
      @NonNull TextView textView7, @NonNull TextView tvDomicile, @NonNull TextView tvName,
      @NonNull TextView tvPos, @NonNull TextView tvPrincipal, @NonNull TextView tvSchoolCode,
      @NonNull TextView tvSchoolName) {
    this.rootView = rootView;
    this.btnSignOut = btnSignOut;
    this.imageView2 = imageView2;
    this.textView10 = textView10;
    this.textView12 = textView12;
    this.textView15 = textView15;
    this.textView2 = textView2;
    this.textView7 = textView7;
    this.tvDomicile = tvDomicile;
    this.tvName = tvName;
    this.tvPos = tvPos;
    this.tvPrincipal = tvPrincipal;
    this.tvSchoolCode = tvSchoolCode;
    this.tvSchoolName = tvSchoolName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNotificationsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNotificationsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_notifications, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNotificationsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSignOut;
      Button btnSignOut = ViewBindings.findChildViewById(rootView, id);
      if (btnSignOut == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView15;
      TextView textView15 = ViewBindings.findChildViewById(rootView, id);
      if (textView15 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.tv_domicile;
      TextView tvDomicile = ViewBindings.findChildViewById(rootView, id);
      if (tvDomicile == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_pos;
      TextView tvPos = ViewBindings.findChildViewById(rootView, id);
      if (tvPos == null) {
        break missingId;
      }

      id = R.id.tv_principal;
      TextView tvPrincipal = ViewBindings.findChildViewById(rootView, id);
      if (tvPrincipal == null) {
        break missingId;
      }

      id = R.id.tv_schoolCode;
      TextView tvSchoolCode = ViewBindings.findChildViewById(rootView, id);
      if (tvSchoolCode == null) {
        break missingId;
      }

      id = R.id.tv_schoolName;
      TextView tvSchoolName = ViewBindings.findChildViewById(rootView, id);
      if (tvSchoolName == null) {
        break missingId;
      }

      return new FragmentNotificationsBinding((ConstraintLayout) rootView, btnSignOut, imageView2,
          textView10, textView12, textView15, textView2, textView7, tvDomicile, tvName, tvPos,
          tvPrincipal, tvSchoolCode, tvSchoolName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
