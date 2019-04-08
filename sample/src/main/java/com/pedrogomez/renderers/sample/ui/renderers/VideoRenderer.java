/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pedrogomez.renderers.sample.ui.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.sample.R;
import com.pedrogomez.renderers.sample.model.Video;
import com.squareup.picasso.Picasso;

/**
 * Abstract class that works as base renderer for Renderer<Video>. This class implements the main
 * render algorithm and declare some abstract methods to be implemented by subtypes.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public abstract class VideoRenderer extends Renderer<Video> {

  @BindView(R.id.iv_thumbnail) ImageView thumbnail;
  @BindView(R.id.tv_title) TextView title;
  @BindView(R.id.iv_marker) ImageView marker;
  @BindView(R.id.tv_label) TextView label;

  /**
   * Inflate the main layout used to render videos in the list view.
   *
   * @param inflater LayoutInflater service to inflate.
   * @param parent ViewGroup used to inflate xml.
   * @return view inflated.
   */
  @Override protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View inflatedView = inflater.inflate(R.layout.video_renderer, parent, false);
        /*
         * You don't have to use ButterKnife library to implement the mapping between your layout
         * and your widgets you can implement setUpView and hookListener methods declared in
         * Renderer<T> class.
         */
    ButterKnife.bind(this, inflatedView);
    return inflatedView;
  }

  @OnClick(R.id.iv_thumbnail) void onVideoClicked() {
    Video video = getContent();
    Toast.makeText(getContext(), "Video clicked. Title = " + video.getTitle(), Toast.LENGTH_LONG)
        .show();
  }

  /**
   * Main render algorithm based on render the video thumbnail, render the title, render the marker
   * and the label.
   */
  @Override public void render() {
    Video video = getContent();
    renderThumbnail(video);
    renderTitle(video);
    renderMarker(video);
    renderLabel();
  }

  /**
   * Use picasso to render the video thumbnail into the thumbnail widget using a temporal
   * placeholder.
   *
   * @param video to get the rendered thumbnail.
   */
  private void renderThumbnail(Video video) {
    Picasso.get().cancelRequest(thumbnail);
    Picasso.get()
        .load(video.getThumbnail())
        .placeholder(R.drawable.placeholder)
        .into(thumbnail);
  }

  /**
   * Render video title into the title widget.
   *
   * @param video to get the video title.
   */
  private void renderTitle(Video video) {
    this.title.setText(video.getTitle());
  }

  protected TextView getLabel() {
    return label;
  }

  protected ImageView getMarker() {
    return marker;
  }

  protected abstract void renderLabel();

  protected abstract void renderMarker(Video video);

  /**
   * Maps all the view elements from the xml declaration to members of this renderer.
   */
  @Override protected void setUpView(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
  }

  /**
   * Insert external listeners in some widgets.
   */
  @Override protected void hookListeners(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
  }
}
