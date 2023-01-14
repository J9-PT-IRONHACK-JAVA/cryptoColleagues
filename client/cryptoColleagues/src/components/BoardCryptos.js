import React, { useState, useEffect } from 'react';

import UserService from '../services/user.service';
import EventBus from '../common/EventBus';

const BoardUser = () => {
	const [content, setContent] = useState([]);

	useEffect(() => {
		UserService.getCryptos().then(
			(response) => {
				setContent(response.data);
			},
			(error) => {
				const _content =
					(error.response &&
						error.response.data &&
						error.response.data.message) ||
					error.message ||
					error.toString();

				setContent(_content);

				if (error.response && error.response.status === 401) {
					EventBus.dispatch('logout');
				}
			},
		);
	}, []);

	return (
		<div className="container">
    <div class="title mt-5 mb-4"><h3>Best cryptos</h3></div>
    <table class="table table-striped table-dark mt-3">
      <thead>
        <tr>
          <th scope="col">Rank</th>
          <th scope="col">Symbol</th>
          <th scope="col">Name</th>
          <th scope="col">Price usd</th>
        </tr>
      </thead>
      <tbody>
     {content &&
        content.map((cont, index) => (
        <tr>
          <td>{cont.rank}</td>
          <td>{cont.symbol}</td>
          <td>{cont.name}</td>
          <td>{`${cont.price_usd} $`}</td>
        </tr>
        ))}
        </tbody>
    </table>
		</div>
	);
};

export default BoardUser;