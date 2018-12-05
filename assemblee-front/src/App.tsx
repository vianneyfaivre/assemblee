import * as React from 'react';
import * as ReactRouter from 'react-router-dom';

import AllPeople from './People/AllPeople';
import AllVotes from './Votes/AllVotes';

import './App.css';

class App extends React.Component {
  public render() {
    return (
      <ReactRouter.BrowserRouter>
          <div>
            <nav>
              <ul>
                <li>
                  <ReactRouter.Link to="/">Députés</ReactRouter.Link>
                </li>
                <li>
                  <ReactRouter.Link to="/scrutins/">Scrutins</ReactRouter.Link>
                </li>
              </ul>
            </nav>

            <ReactRouter.Route path="/" exact={true} component={AllPeople} />
            <ReactRouter.Route path="/scrutins/" component={AllVotes} />
          </div>
        </ReactRouter.BrowserRouter>
    );
  }
}

export default App;
