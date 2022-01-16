import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './def-item.reducer';
import { IDefItem } from 'app/shared/model/def-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DefItem = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const defItemList = useAppSelector(state => state.defItem.entities);
  const loading = useAppSelector(state => state.defItem.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="def-item-heading" data-cy="DefItemHeading">
        <Translate contentKey="startpointApp.defItem.home.title">Def Items</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="startpointApp.defItem.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="startpointApp.defItem.home.createLabel">Create new Def Item</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {defItemList && defItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="startpointApp.defItem.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="startpointApp.defItem.code">Code</Translate>
                </th>
                <th>
                  <Translate contentKey="startpointApp.defItem.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="startpointApp.defItem.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="startpointApp.defItem.parent">Parent</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {defItemList.map((defItem, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${defItem.id}`} color="link" size="sm">
                      {defItem.id}
                    </Button>
                  </td>
                  <td>{defItem.code}</td>
                  <td>{defItem.name}</td>
                  <td>{defItem.type ? <Link to={`def-type/${defItem.type.id}`}>{defItem.type.id}</Link> : ''}</td>
                  <td>{defItem.parent ? <Link to={`def-item/${defItem.parent.id}`}>{defItem.parent.code}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${defItem.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${defItem.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${defItem.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="startpointApp.defItem.home.notFound">No Def Items found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DefItem;
