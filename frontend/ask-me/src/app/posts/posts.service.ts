import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Posts } from '@app/_models/posts'
import { Channel } from '@app/_models/channel'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) { }

  getChannelDetails(channelId) : Observable<Channel>{
    // console.log(channelId)
    let jsonLink = 'assets/data/channel'+channelId+'.json'
    let apiLink = `${environment.apiUrl}/api/channels/`+channelId
    let link = apiLink

    return this.http.get<Channel>(link)
  }

  getPosts(channelId) : Observable<Posts>{
    // console.log(channelId)
    let jsonLink = 'assets/data/post'+channelId+'.json'
    let apiLink = `${environment.apiUrl}/api/channels/`+channelId+`/posts`
    let link = apiLink

    return this.http.get<Posts>(link)
  }
}
