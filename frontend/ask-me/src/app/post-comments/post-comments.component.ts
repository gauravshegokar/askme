import {Router, ActivatedRoute, Params} from '@angular/router';
import { FormGroup,FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { PostDetails } from "@app/_models/postDetails";
import { PostComments } from "@app/_models/postComments";
import { PostCommentsService } from "@app/post-comments/post-comments.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-post-comments',
  templateUrl: './post-comments.component.html',
  styleUrls: ['./post-comments.component.scss']
})
export class PostCommentsComponent implements OnInit {

  form: FormGroup = new FormGroup({
    selComment : new FormControl('')
  });

  error:string|null
  constructor(private postCommentsService:PostCommentsService,private activatedRoute: ActivatedRoute,private router:Router) { }

  public postDetailsData : PostDetails
  public postCommentsData : PostComments

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const postId = params['postId'];
      this.loadPostDetails(postId);
      this.loadPostComments(postId);
    });
  }

  loadPostDetails(postId){
    this.postCommentsService.getPostDetails(postId).subscribe(
      response=>{
        console.log(response)
        this.postDetailsData=response
      },
      err=>{
        console.log(err)
      }
    )
  }

  loadPostComments(postId){
    this.postCommentsService.getComments(postId).subscribe(
      response=>{
        console.log(response)
        this.postCommentsData=response
      },
      err=>{
        console.log(err)
      }
    )
  }

  submit(pId){
    console.log(this.form.value.selComment);

    this.postCommentsService.newCommentPublish(this.form.value.selComment,pId)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['/postCommentsPath'],{ queryParams: { postId : pId } });
        },
        error => {
          console.log(error);
          this.error=error;
        }
      );
  }

}
