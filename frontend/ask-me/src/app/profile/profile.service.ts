import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Profile } from '@app/_models/profile';
import { Observable } from 'rxjs';
import { Interests } from "@app/_models/interests";
import { Followers } from "@app/_models/followers";
import { UserPosts } from "@app/_models/userPosts";
import { OwnedChannels } from "@app/_models/ownedChannels";
import { SubscribedChannels } from "@app/_models/subscribedChannels";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  getProfileDetails(profileId): Observable<Profile> {
    // console.log(profileId)
    let jsonLink = 'assets/data/profile' + profileId + '.json'
    let apiLink = `${environment.apiUrl}/api/users/` + profileId
    let link = apiLink
    return this.http.get<Profile>(link)
  }

  // TODO:
  getInterests(profileId): Observable<Interests> {
    // console.log(profileId)
    let jsonLink = 'assets/data/interests' + profileId + '.json'
    let apiLink = `${environment.apiUrl}/api/users/` + profileId + `/interests`
    let link = jsonLink

    return this.http.get<Interests>(link)
  }

  // TODO:
  getFollowers(profileId): Observable<Followers> {
    // console.log(profileId)
    let jsonLink = 'assets/data/followers' + profileId + '.json'
    let apiLink = `${environment.apiUrl}/api/users/` + profileId + `/followers`
    let link = jsonLink

    return this.http.get<Followers>(link)
  }

  getUserPosts(profileId): Observable<UserPosts> {
    // console.log(profileId)
    let jsonLink = 'assets/data/userpost' + profileId + '.json'
    let apiLink = `${environment.apiUrl}/api/users/` + profileId + `/posts`
    let link = apiLink

    return this.http.get<UserPosts>(link)
  }

  // TODO:
  getOwnedChannels(profileId): Observable<OwnedChannels> {
    // console.log(profileId)
    let jsonLink = 'assets/data/ownedChannels' + profileId + '.json'
    let apiLink = `${environment.apiUrl}/api/users/` + profileId + `/ownedchannels`
    let link = jsonLink

    return this.http.get<OwnedChannels>(link)
  }

  getSubscribedChannels(profileId): Observable<SubscribedChannels> {
    // console.log(profileId)
    let jsonLink = 'assets/data/subscribedChannels' + profileId + '.json'
    let apiLink = `${environment.apiUrl}/api/users/` + profileId + `/subscribedchannels`
    let link = apiLink

    return this.http.get<SubscribedChannels>(link)
  }
}
