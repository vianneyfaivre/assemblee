import * as React from 'react';
import * as ReactRouter from 'react-router-dom';
import PersonSearchPage from 'src/pages/PersonSearchPage';
import PersonDetailsPage from './pages/PersonDetailsPage';

export default class App extends React.Component {

  public render() {
    return (
      <ReactRouter.BrowserRouter>
          <div>
            <nav className="level-left">
              <p className="level-item has-text-centered">
                <ReactRouter.Link to="/recherche-depute" className="link is-info">Rechercher un député</ReactRouter.Link>
              </p>
            </nav>

            <ReactRouter.Route path="/recherche-depute" exact={true} component={PersonSearchPage} />
            <ReactRouter.Route path="/depute/:id" exact={true} component={PersonDetailsPage} />
            <ReactRouter.Route path="/" exact={true} component={PersonSearchPage} />
          </div>
        </ReactRouter.BrowserRouter>
    );
  }
}
