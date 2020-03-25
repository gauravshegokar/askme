import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Feed } from '@app/_models/feed'
import { Observable } from 'rxjs';
// import { feed } from 'src/assets/data/feed.json';


@Injectable({
  providedIn: 'root'
})
export class FeedService {

  constructor(private http: HttpClient) { }

  getFeed() : Observable<Feed>{
    let jsonLink = 'assets/data/feed.json'
    let apiLink = `${environment.apiUrl}/api/feed`
    let link = apiLink

    return this.http.get<Feed>(link)
  }
}
