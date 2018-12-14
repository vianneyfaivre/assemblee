import * as React from 'react';
import * as ReactRouter from 'react-router-dom';

import AllPeople from 'src/components/people/AllPeople';

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

            <ReactRouter.Route path="/" exact={true} component={AllPeople} />
          </div>
        </ReactRouter.BrowserRouter>
    );
  }
}
