import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { NewPostsService } from "@app/new-post/new-posts.service";
import { first } from 'rxjs/operators'
import { SubscribedChannels } from "@app/_models/subscribedChannels";
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {

  form: FormGroup = new FormGroup({
    selChannel: new FormControl(''),
    selPostContent: new FormControl(''),
    selProfanityFiltering: new FormControl(''),
    selTags: new FormControl('')
  });

  error: string | null
  constructor(private newPostsService: NewPostsService,
    private router: Router) { }

  public channelsData: SubscribedChannels

  ngOnInit(): void {
    this.loadChannels()
  }

  loadChannels() {
    this.newPostsService.getChannels().subscribe(
      response => {
        this.channelsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  findHashtags(hashtagsText) {
    var regexp = /(\s|^)\#\w\w+\b/gm
    let result = hashtagsText.match(regexp);
    if (result) {
      result = result.map(function(s){ return s.trim();});
      // console.log(result);
      return String(result);
    } else {
      return '';
    }
  }

  submit() {
    // console.log(this.form.value.selChannel);
    // console.log(this.form.value.selPostContent);
    // console.log(this.form.value.selProfanityFiltering);
    // console.log(this.form.value.selTags);

    let hashtags = this.findHashtags(this.form.value.selTags)
    this.newPostsService.newPostPublish(this.form.value.selChannel, this.form.value.selPostContent, this.form.value.selProfanityFiltering, hashtags).subscribe(
      response => {
        if (response.status == 201) {
          this.router.navigate(['/']);
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
