import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { SubscribedChannels } from "@app/_models/subscribedChannels";
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, NgZone, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { User } from "@app/_models";
import { NewPost } from "@app/_models/newPost";
import { AuthenticationService } from '@app/_services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class NewPostsService {

  constructor(private http: HttpClient, private _ngZone: NgZone,
    private authenticationService: AuthenticationService) { }

  @ViewChild('autosize') autosize: CdkTextareaAutosize;

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
      .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  getChannels(): Observable<SubscribedChannels> {
    let jsonLink = 'assets/data/channels.json'
    let apiLink = `${environment.apiUrl}/api/channels`
    let apiSubscribedChannels = `${environment.apiUrl}/api/users/` + this.authenticationService.currentUserValue.id + `/subscribedchannels`
    let link = apiSubscribedChannels

    console.log(apiSubscribedChannels)

    return this.http.get<SubscribedChannels>(apiSubscribedChannels)
  }

  newPostPublish(selChannel: string, selPostContent: string, selProfanityFiltering: string, selTags: string) {
    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"
    let apiLink = `${environment.apiUrl}/api/channels/` + selChannel + `/post`

    let jsonData = {
      postText: selPostContent,
      channelId: selChannel,
      tags: selTags,
      profane: selProfanityFiltering
    }

    let link = apiLink
    return this.http.post<any>(link, jsonData, { observe: 'response' })
  }

}
