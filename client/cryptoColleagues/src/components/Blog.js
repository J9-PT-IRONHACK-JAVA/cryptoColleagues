import React, { useState, useEffect } from 'react';

import UserService from '../services/user.service';
import EventBus from '../common/EventBus';

const Blog = () => {
	const [posts, setPost] = useState(null);

	useEffect(() => {
		UserService.getPosts().then(
			(response) => {
				setPost(response.data);
			},
			(error) => {
				const _content =
					(error.response &&
						error.response.data &&
						error.response.data.message) ||
					error.message ||
					error.toString();

          setPost(_content);

				if (error.response && error.response.status === 401) {
					EventBus.dispatch('logout');
				}
			},
		);
	}, []);

	return (
		<div className="container">
      <div class="title mt-5 mb-2 mb-4"><h3>Posts</h3></div>
      {posts && posts.map((post, index)=> {
        return (
          <div key={index} className="custom-card p-4 p-4 mb-4">
          {/* <img alt="img" src={require(`../assets/images/image-${index}.JPG`)}></img> */}
          <p className="mt-4">{post.title}</p>
          <p className="mt-4 notice-description">{post.content}</p>
          <p className='notice-footer mb-0'>
            <span>{post.author.username}</span>
            
            <span> | </span>
            <span><a className='notice-link' href={`mailto:${post.author.email}`} target="_blank" rel="noreferrer"><span>{post.author.email}</span></a></span>
          </p>
        </div>
        )
      })}
		</div>
	);
};

export default Blog;