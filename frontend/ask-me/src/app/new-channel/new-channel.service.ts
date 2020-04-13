import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, NgZone, ViewChild } from '@angular/core';
import { map, take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NewChannelService {

  constructor(private http: HttpClient, private _ngZone: NgZone) { }

  @ViewChild('autosize') autosize: CdkTextareaAutosize;

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
      .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  newChannelPublish(selChannelName: string, selChannelDesc: string) {
    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"
    let apiLink = `${environment.apiUrl}/api/channels`

    let jsonData = {
      channelName: selChannelName,
      channelDescription: selChannelDesc
    }

    let link = mockLink
    return this.http.post<any>(link, jsonData, { observe: 'response' })
  }

}
