import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { PostDetails } from "@app/_models/postDetails";
import { PostComments } from "@app/_models/postComments";
import { PostCommentsService } from "@app/post-comments/post-comments.service";
import { first } from "rxjs/operators";

@Component({
  selector: 'app-post-comments',
  templateUrl: './post-comments.component.html',
  styleUrls: ['./post-comments.component.scss']
})
export class PostCommentsComponent implements OnInit {

  form: FormGroup = new FormGroup({
    selComment: new FormControl('')
  });

  error: string | null
  constructor(private postCommentsService: PostCommentsService, private activatedRoute: ActivatedRoute, private router: Router) { }

  public postDetailsData: PostDetails
  public postCommentsData: PostComments

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const postId = params['postId'];
      this.loadPostDetails(postId);
      this.loadPostComments(postId);
    });
  }

  loadPostDetails(postId) {
    this.postCommentsService.getPostDetails(postId).subscribe(
      response => {
        this.postDetailsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadPostComments(postId) {
    this.postCommentsService.getComments(postId).subscribe(
      response => {
        this.postCommentsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  submit(pId) {

    this.postCommentsService.newCommentPublish(this.form.value.selComment, pId).subscribe(
      response => {
        if (response.status == 201) {
          this.loadPostComments(pId)
        } else {
          this.error = "Something went wrong"
        }
      },
      err => {
        this.error = err.error.error
        console.log(err)
      }
    )
  }
}
