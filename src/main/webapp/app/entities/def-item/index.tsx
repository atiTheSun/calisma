import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefItem from './def-item';
import DefItemDetail from './def-item-detail';
import DefItemUpdate from './def-item-update';
import DefItemDeleteDialog from './def-item-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DefItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DefItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DefItemDetail} />
      <ErrorBoundaryRoute path={match.url} component={DefItem} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DefItemDeleteDialog} />
  </>
);

export default Routes;
