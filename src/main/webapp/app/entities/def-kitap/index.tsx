import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefKitap from './def-kitap';
import DefKitapDetail from './def-kitap-detail';
import DefKitapUpdate from './def-kitap-update';
import DefKitapDeleteDialog from './def-kitap-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DefKitapUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DefKitapUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DefKitapDetail} />
      <ErrorBoundaryRoute path={match.url} component={DefKitap} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DefKitapDeleteDialog} />
  </>
);

export default Routes;
