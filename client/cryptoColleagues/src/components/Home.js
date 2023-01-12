import React, { useState, useEffect } from 'react';

import UserService from '../services/user.service';

const Home = () => {
	const [cryptoNews, setCryptoNews] = useState(null);

	useEffect(() => {
		UserService.getNews().then(
			(response) => {
				setCryptoNews(response.data.data);
				console.log(response.data)
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
		<div className="container">
			<div class="row">
			{cryptoNews &&
				cryptoNews.map((cryptoNew, index) => (
					<div key={index} className="card">
						<p>{cryptoNew.title}</p>
						<p className='notice-description'>{cryptoNew.description}</p>
						<p className='notice-footer'>
							<span>{cryptoNew.source}</span>
							<span> | </span>
							<span><a className='notice-link' href='cryptoNew.url' target="_blank">{cryptoNew.source}</a></span>
						</p>
					</div>
				))}
			</div>
		</div>
	);
};

export default Home;
