import * as React from 'react';
import * as ReactRouter from 'react-router-dom';
import PersonSearchPage from 'src/pages/PersonSearchPage';
import PersonDetailsPage from 'src/pages/PersonDetailsPage';

export default class App extends React.Component {

  public render() {
    return (
      <ReactRouter.BrowserRouter>
        <div>

          <nav className="navbar has-background-light" role="navigation" aria-label="main navigation">

            <div className="navbar-brand">
              <span className="navbar-item is-size-4">🏛️ Mon Assemblée</span>
              <ReactRouter.NavLink to="/recherche-depute" className="navbar-item">Rechercher un député</ReactRouter.NavLink>
            </div>
          </nav>

          <ReactRouter.Route path="/recherche-depute" exact={true} component={PersonSearchPage} />
          <ReactRouter.Route path="/depute/:id" exact={true} component={PersonDetailsPage} />
          <ReactRouter.Route path="/" exact={true} component={PersonSearchPage} />

        </div>
      </ReactRouter.BrowserRouter>
    );
  }
}
