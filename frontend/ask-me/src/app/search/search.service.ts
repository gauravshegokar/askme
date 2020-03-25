import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {map} from "rxjs/operators";
import {NewPost} from "@app/_models/newPost";
import {Feed} from "@app/_models/feed";
import {SearchFeed} from "@app/_models/searchFeed";

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  search(selKey: string,feedData: Feed) {
    // console.log(feedData)
    let mockLink = "http://www.mocky.io/v2/5e72ac8f3300008c0044ca04"
    let apiLink = `${environment.apiUrl}/api/search?keywords=`+selKey

    let link = apiLink
    return this.http.get<SearchFeed>(link)
      .pipe(map(response => {
        console.log(response)
        return response;
      }));
  }

}
