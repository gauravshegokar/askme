import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Channels } from "@app/_models/channels";
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, NgZone, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import {SubscribeChannelService} from "@app/channels-list/subscribe-channel.service";
import {DeleteChannelService} from "@app/channels-list/delete-channel.service";


@Injectable({
  providedIn: 'root'
})
export class ChannelsListService {

  constructor(private http: HttpClient, private _ngZone: NgZone,
              private subscribeChannelService: SubscribeChannelService,
              private  deleteChannelService: DeleteChannelService) { }

  @ViewChild('autosize') autosize: CdkTextareaAutosize;

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
      .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  getChannels(): Observable<Channels> {
    let jsonLink = 'assets/data/channels.json'
    let apiLink = `${environment.apiUrl}/api/channels`
    let link = apiLink

    return this.http.get<Channels>(link)
  }

  channelAction(selChannel: string,selAction: string) {
    console.log(selAction)

    if(selAction == "Subscribe"){
      return this.subscribeChannelService.executeAction(selChannel)
    }else if(selAction == "Delete"){
      return this.deleteChannelService.executeAction(selChannel)
    }
  }

}
