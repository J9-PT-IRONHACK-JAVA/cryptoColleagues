import React, { useState, useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import UserService from '../services/user.service';

const Profile = () => {
	const [portfolio, setPortfolio] = useState(null);

	const { user: currentUser } = useSelector((state) => state.auth);

	useEffect(() => {
		if(!!currentUser) {
			UserService.getPortfolio(currentUser.id).then(
				(response) => {
					setPortfolio(response.data);
		
				},
				(error) => {
					const _content =
						(error.response && error.response.data) ||
						error.message ||
						error.toString();
	
						setPortfolio(_content);
				},
			);
		}
	}, [currentUser]);

	if (!currentUser) {
		return <Navigate to="/login" />;
	}

	return (
		<div className="container">
			<div class="title mt-5 mb-2"><h3>Profile</h3></div>
			<div className="custom-card p-4 p-4 mt-3 mb-4 avatar-container">
				<img className='avatar-img' alt="img" src={require(`../assets/images/avatar.png`)}></img>
				<div>
					<p className="mb-2"><span className="pink">{currentUser.username}</span></p>
					<p className="mb-2">Id: <span className="pink">{currentUser.id}</span></p>
					<p className="mb-2">Email: <span className="pink">{currentUser.email}</span></p>
					<span>Authorities:</span>
					{currentUser.roles &&
					currentUser.roles.map((role, index) => <li key={index}><span className="pink">{role}</span></li>)}
				</div>
			</div>
			<div class="title mt-5 mb-2"><h3>Portfolio</h3></div>
			<div className="custom-card p-4 p-4 mt-3 mb-4 avatar-container d-flex flex-column w-100">
				{portfolio && portfolio.map((port) => {
					return (
						<div className="w-100 mb-4 crypto-divider">
							<p className="mb-2">Name: <span className="pink">{port.name}</span></p>
							<p className="mb-2">Description: <span className="pink">{port.description}</span></p>
							<div className='ms-3'>
								<div class="title mt-3 mb-2"><h3 className="cryptos-title">My Cryptos</h3></div>
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
										{port.cryptoCurrencies && port.cryptoCurrencies.map((crypto => {
											return(
												<tr>
													<td>{crypto.coinRank}</td>
													<td>{crypto.symbol}</td>
													<td>{crypto.name}</td>
													<td>{`${crypto.coinPrice} $`}</td>
											</tr>
											)
										}))}
									</tbody>
								</table>
							</div>
						</div>
					)
				})}
			</div>
		</div>
	);
};

export default Profile;
