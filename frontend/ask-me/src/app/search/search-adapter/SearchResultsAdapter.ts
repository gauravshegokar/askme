import { SearchResults } from './SearchResults'
import { SearchService } from '../search.service'
import { first, map } from "rxjs/operators";
import { Injectable } from '@angular/core';
import { observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SearchResultAdapter implements SearchResults {
    constructor(private searchService: SearchService) {
    }

    getSearchResults(keywords: string) {
        let searchResult = this.searchService.search(keywords)
        return searchResult.pipe(map(results => {
            let op = results
            op.posts.forEach(item => {
                let p = item
                p.datePosted = new Date(p.datePosted)
                return p
            }
            )
            return op
        }))
    }
}