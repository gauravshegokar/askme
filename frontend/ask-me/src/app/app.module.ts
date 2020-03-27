import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { MatSliderModule } from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';

import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';

import { JwtInterceptor } from './_helpers';
import { NavbarComponent } from './navbar/navbar.component';
import { FeedComponent } from './feed/feed.component';
import { PostsComponent } from './posts/posts.component';
import { NewPostComponent } from './new-post/new-post.component';
import { MatSelectModule } from "@angular/material/select";
import { MatRadioModule } from "@angular/material/radio";
import { ProfileComponent } from './profile/profile.component';
import { MatGridListModule } from "@angular/material/grid-list";
import { PostCommentsComponent } from './post-comments/post-comments.component';
import { SearchComponent } from './search/search.component';
import { MatListModule } from '@angular/material/list';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    FeedComponent,
    PostsComponent,
    NewPostComponent,
    ProfileComponent,
    PostCommentsComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    MatCardModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSelectModule,
    MatRadioModule,
    MatGridListModule,
    MatListModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
