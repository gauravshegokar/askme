import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
import { NewPostsService} from "@app/new-post/new-posts.service";
import { first } from 'rxjs/operators'
import { Channels} from "@app/_models/channels";
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {

  form: FormGroup = new FormGroup({
    selChannel : new FormControl(''),
    selPostContent : new FormControl(''),
    selProfanityFiltering : new FormControl(''),
    selTags : new FormControl('')
  });

  error:string|null
  constructor(private newPostsService:NewPostsService,
              private router:Router) { }

  public channelsData: Channels

  ngOnInit(): void {
    this.loadChannels()
  }

  loadChannels(){
    this.newPostsService.getChannels().subscribe(
      response=>{
        console.log(response)
        this.channelsData=response
      },
      err=>{
        console.log(err)
      }
    )
  }

  submit(){
    console.log(this.form.value.selChannel);
    console.log(this.form.value.selPostContent);
    console.log(this.form.value.selProfanityFiltering);
    console.log(this.form.value.selTags);

    this.newPostsService.newPostPublish(this.form.value.selChannel,this.form.value.selPostContent,this.form.value.selProfanityFiltering,this.form.value.selTags)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['/']);
        },
        error => {
          console.log(error);
          this.error=error;
        }
      );
  }
}
