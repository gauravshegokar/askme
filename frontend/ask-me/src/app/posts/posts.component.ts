import { Router, ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PostsService } from "@app/posts/posts.service";
import { Posts } from "@app/_models/posts";
import { Channel } from "@app/_models/channel";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  constructor(private postsService: PostsService, private activatedRoute: ActivatedRoute, private router: Router) { }

  public postsData: Posts
  public channelData: Channel

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const channelId = params['channelId'];
      this.loadChannelDetails(channelId)
      this.loadPosts(channelId)
    });
  }

  loadChannelDetails(channelId) {
    this.postsService.getChannelDetails(channelId).subscribe(
      response => {
        // console.log(response)
        this.channelData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadPosts(channelId) {
    this.postsService.getPosts(channelId).subscribe(
      response => {
        // console.log(response)
        this.postsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  postComments(pId) {
    this.router.navigate(['postCommentsPath'], { queryParams: { postId: pId } })
  }

}
