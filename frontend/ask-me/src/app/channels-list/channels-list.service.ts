import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Channels } from "@app/_models/channels";
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, NgZone, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ChannelsListService {

  constructor(private http: HttpClient, private _ngZone: NgZone) { }

  @ViewChild('autosize') autosize: CdkTextareaAutosize;

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
      .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  getChannels(): Observable<Channels> {
    let jsonLink = 'assets/data/channels.json'
    let apiLink = `${environment.apiUrl}/api/channels`
    let link = jsonLink

    return this.http.get<Channels>(link)
  }

  subscribeChannel(selChannel: string,selAction: string) {
    console.log(selChannel)
    console.log(selAction)
    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"


    let jsonData = {
    }

    if(selAction == "Subscribe"){
      let apiLink = `${environment.apiUrl}/api/channels/` + selChannel + `/subscribe`
      let link = mockLink
      return this.http.post<any>(link, jsonData, { observe: 'response' })
    }else{
      let apiLink = `${environment.apiUrl}/api/channels/` + selChannel
      let link = mockLink
      return this.http.delete(link, { observe: 'response' })
    }
  }

}
