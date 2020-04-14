import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FeedService } from '@app/feed/feed.service';
// import { SearchService } from "@app/search/search.service";
import { Feed } from '@app/_models/feed';
import { first } from "rxjs/operators";
import { FormControl, FormGroup } from "@angular/forms";
import { SearchFeed } from "@app/_models/searchFeed";
import { SearchResultAdapter } from "./search-adapter/SearchResultsAdapter"

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  constructor(private feedService: FeedService, private router: Router, private searchResultAdapter: SearchResultAdapter, private activatedRoute: ActivatedRoute) { }

  public feedData: Feed
  public searchData: SearchFeed
  form: FormGroup = new FormGroup({
    searchKeywords: new FormControl('')
  });

  error: string | null

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.loadFeed()
    });
  }

  loadFeed() {
    this.feedService.getFeed().subscribe(
      response => {
        this.feedData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  search() {

    this.searchResultAdapter.getSearchResults(this.form.value.searchKeywords)
      .subscribe(
        data => {
          this.searchData = data
        },
        error => {
          console.log(error);
          this.error = error;
        }
      );
  }

  channelPosts(chId) {
    this.router.navigate(['posts'], { queryParams: { channelId: chId } })
  }

  postDetails(pId) {
    this.router.navigate(['postCommentsPath'], { queryParams: { postId: pId } })
  }

}
