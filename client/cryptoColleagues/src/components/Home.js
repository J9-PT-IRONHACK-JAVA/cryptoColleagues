import React, { useState, useEffect } from 'react';

import UserService from '../services/user.service';


const Home = () => {
	const [cryptoNews, setCryptoNews] = useState(null);

	useEffect(() => {
		UserService.getNews().then(
			(response) => {
				setCryptoNews(response.data.data);
			},
			(error) => {
				const _content =
					(error.response && error.response.data) ||
					error.message ||
					error.toString();

					setCryptoNews(_content);
			},
		);
	}, []);

	return (
		<div>
			<div className="title mt-5 mb-4">
				<h3>Last cryptoNews</h3>
			</div>
			<div class="container-card">
			{cryptoNews &&
				cryptoNews.map((cryptoNew, index) => (
	
					<div key={index} className="custom-card p-4 p-4">
						<img alt="img" src={require(`../assets/images/image-${index}.JPG`)}></img>
						<p className="mt-4">{cryptoNew.title}</p>
						<p className='notice-description'>{cryptoNew.description}</p>
						<p className='notice-footer mb-0'>
							<span>{cryptoNew.source}</span>
							<span> | </span>
							<span><a className='notice-link' href={cryptoNew.url} target="_blank" rel="noreferrer">{cryptoNew.source}</a></span>
						</p>
					</div>
	
				))}
			</div>
		</div>
	);
};

export default Home;
