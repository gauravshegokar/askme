import { Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Channels} from "@app/_models/channels";
import {CdkTextareaAutosize} from '@angular/cdk/text-field';
import {Component, NgZone, ViewChild} from '@angular/core';
import { Observable } from 'rxjs';
import {map, take} from 'rxjs/operators';
import {User} from "@app/_models";
import {NewPost} from "@app/_models/newPost";

@Injectable({
  providedIn: 'root'
})
export class NewPostsService {

  constructor(private http: HttpClient,private _ngZone: NgZone) { }

  @ViewChild('autosize') autosize: CdkTextareaAutosize;

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
      .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  getChannels() : Observable<Channels>{
    let jsonLink = 'assets/data/channels.json'
    let apiLink = `${environment.apiUrl}/api/channels`
    let link = jsonLink

    return this.http.get<Channels>(link)
  }

  newPostPublish(selChannel: string, selPostContent: string, selProfanityFiltering: string, selTags: string) {
    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"
    let apiLink = `${environment.apiUrl}/api/channels/`+selChannel+`/post`

    let link = mockLink
    return this.http.get<any>(link)
      .pipe(map(response => {
        console.log(response)

        let newPost = new NewPost()
        newPost.id = response.auth

        return newPost;
      }));
  }

}
