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
    console.log(postId)
    let jsonLink = 'assets/data/postDetails'+postId+'.json'
    let apiLink = `${environment.apiUrl}/api/posts/`+postId
    let link = jsonLink

    return this.http.get<PostDetails>(link)
  }

  getComments(postId) : Observable<PostComments>{
    console.log(postId)
    let jsonLink = 'assets/data/postComments'+postId+'.json'
    let apiLink = `${environment.apiUrl}/api/posts/`+postId+`/comments`
    let link = jsonLink

    return this.http.get<PostComments>(link)
  }

  newCommentPublish(selComment: string,postId: string) {
    console.log(postId)
    let mockLink = "http://www.mocky.io/v2/5e70f6df30000029007a3374"
    let apiLink = `${environment.apiUrl}/api/posts/`+postId+`/comments`

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
