import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Component, NgZone, ViewChild } from '@angular/core';
import {ActionsService} from "@app/channels-list/actions-service";


@Injectable({
  providedIn: 'root'
})
export class DeleteChannelService implements ActionsService{
  constructor(private http: HttpClient, private _ngZone: NgZone) { }
  executeAction(selChannel: string) {
    console.log(selChannel)

    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"
    let apiLink = `${environment.apiUrl}/api/channels/` + selChannel
    let jsonData = {
    }
    let link = apiLink
    return this.http.delete(link, { observe: 'response' })
  }


}
