import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {LoginComponent} from './login/login.component'
import {RegisterComponent} from './register/register.component'
import { FeedComponent } from './feed/feed.component';
import { PostsComponent } from './posts/posts.component';
import { NewPostComponent} from "@app/new-post/new-post.component"
import { ProfileComponent} from "@app/profile/profile.component";
import { PostCommentsComponent } from "@app/post-comments/post-comments.component";
import { SearchComponent } from "@app/search/search.component";
import {NewChannelComponent} from "@app/new-channel/new-channel.component";
import {ChannelsListComponent} from "@app/channels-list/channels-list.component";

const routes: Routes = [
  { path: '', component: FeedComponent},
  { path: 'login', component: LoginComponent},
  { path: 'signup', component: RegisterComponent},
  { path: 'posts', component: PostsComponent},
  { path: 'newPost', component: NewPostComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'postCommentsPath', component: PostCommentsComponent},
  { path: 'searchPath', component: SearchComponent},
  { path: 'newChannel', component: NewChannelComponent},
  { path: 'channelsList', component: ChannelsListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
