import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeedService } from './feed.service'
import { Feed } from '@app/_models/feed';
@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  constructor(private feedService: FeedService,private router:Router) { }

  public feedData : Feed

  ngOnInit(): void {
    this.loadFeed()
  }



  loadFeed(){
    this.feedService.getFeed().subscribe(
      response => {
        // console.log(response)
        this.feedData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  channelPosts(chId){
    this.router.navigate(['posts'], { queryParams: { channelId : chId } })
  }

}
