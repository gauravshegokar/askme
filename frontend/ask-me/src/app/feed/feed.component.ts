import { Component, OnInit } from '@angular/core';
import { FeedService } from './feed.service'
import { Feed } from '@app/_models/feed';
@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  constructor(private feedService: FeedService) { }

  public feedData : Feed

  ngOnInit(): void {
    this.loadFeed()
  }



  loadFeed(){
    this.feedService.getFeed().subscribe(
      response => {
        console.log(response)
        this.feedData = response
      },
      err => {
        console.log(err)
      }
    )
  }

}
