export interface Posts {
    posts: {
        postId: number,
        postText: string,
        profane: boolean,
        author: string,
        authorId: number,
        likesCount: number,
        datePosted: string,
        tags:{
            tagId: number,
            tagName: string
        }
    }
}
