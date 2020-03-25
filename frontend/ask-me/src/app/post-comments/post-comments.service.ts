import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {PostComments} from "@app/_models/postComments";
import {PostDetails} from "@app/_models/postDetails";
import {map} from "rxjs/operators";
import {NewPost} from "@app/_models/newPost";

@Injectable({
  providedIn: 'root'
})
export class PostCommentsService {

  constructor(private http: HttpClient) { }

  getPostDetails(postId) : Observable<PostDetails>{
    let jsonLink = 'assets/data/postDetails'+postId+'.json'
    let apiLink = `${environment.apiUrl}/api/posts/`+postId
    let link = apiLink

    return this.http.get<PostDetails>(link)
  }

  getComments(postId) : Observable<PostComments>{
    let jsonLink = 'assets/data/postComments'+postId+'.json'
    let apiLink = `${environment.apiUrl}/api/posts/`+postId+`/comments`
    let link = apiLink

    return this.http.get<PostComments>(link)
  }

  newCommentPublish(selComment: string,postId: string) {
    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"
    let apiLink = `${environment.apiUrl}/api/posts/`+postId+`/comments`

    let jsonData = {
      commentText: selComment
    }

    let link = apiLink
    return this.http.post<any>(link, jsonData, { observe: 'response' })
  }
}
