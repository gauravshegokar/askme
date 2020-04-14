import { Router, ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ProfileService } from "@app/profile/profile.service";
import { Profile } from '@app/_models/profile';
import { Interests } from "@app/_models/interests";
import { Followers } from "@app/_models/followers";
import { UserPosts } from "@app/_models/userPosts";
import { OwnedChannels } from "@app/_models/ownedChannels";
import { SubscribedChannels } from "@app/_models/subscribedChannels";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(private profileService: ProfileService, private activatedRoute: ActivatedRoute, private router: Router) { }

  public profileData: Profile
  public interestsData: Interests
  public followersData: Followers
  public userPostsData: UserPosts
  public ownedChannelsData: OwnedChannels
  public subscribedChannelsData: SubscribedChannels

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const profileId = params['profileId'];
      this.loadProfileDetails(profileId);
      this.loadInterests(profileId);
      this.loadFollowers(profileId);
      this.loadUserPosts(profileId);
      this.loadOwnedChannels(profileId)
      this.loadSubscribedChannels(profileId)
    });
  }

  loadProfileDetails(profileId) {
    this.profileService.getProfileDetails(profileId).subscribe(
      response => {
        // console.log(response)
        this.profileData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadInterests(profileId) {
    this.profileService.getInterests(profileId).subscribe(
      response => {
        // console.log(response)
        this.interestsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadFollowers(profileId) {
    this.profileService.getFollowers(profileId).subscribe(
      response => {
        // console.log(response)
        this.followersData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadUserPosts(profileId) {
    this.profileService.getUserPosts(profileId).subscribe(
      response => {
        // console.log(response)
        this.userPostsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadOwnedChannels(profileId) {
    this.profileService.getOwnedChannels(profileId).subscribe(
      response => {
        console.log(response)
        this.ownedChannelsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  loadSubscribedChannels(profileId) {
    this.profileService.getSubscribedChannels(profileId).subscribe(
      response => {
        console.log(response)
        this.subscribedChannelsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  postDetails(pId) {
    this.router.navigate(['postCommentsPath'], { queryParams: { postId: pId } })
  }

}
