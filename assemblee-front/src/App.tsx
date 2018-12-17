import * as React from 'react';
import * as ReactRouter from 'react-router-dom';

import PersonHomePage from 'src/components/people/PersonHomePage';

import './App.css';

export default class App extends React.Component {
  public render() {
    return (
      <ReactRouter.BrowserRouter>
          <div>
            <nav className="level-left">
              <p className="level-item has-text-centered">
                <ReactRouter.Link to="/" className="link is-info">Députés</ReactRouter.Link>
              </p>
            </nav>

            <ReactRouter.Route path="/" exact={true} component={PersonHomePage} />
          </div>
        </ReactRouter.BrowserRouter>
    );
  }
}
