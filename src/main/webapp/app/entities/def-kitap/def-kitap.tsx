import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './def-kitap.reducer';
import { IDefKitap } from 'app/shared/model/def-kitap.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DefKitap = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const defKitapList = useAppSelector(state => state.defKitap.entities);
  const loading = useAppSelector(state => state.defKitap.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="def-kitap-heading" data-cy="DefKitapHeading">
        <Translate contentKey="startpointApp.defKitap.home.title">Def Kitaps</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="startpointApp.defKitap.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="startpointApp.defKitap.home.createLabel">Create new Def Kitap</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {defKitapList && defKitapList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="startpointApp.defKitap.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="startpointApp.defKitap.isbn">Isbn</Translate>
                </th>
                <th>
                  <Translate contentKey="startpointApp.defKitap.kitapAdi">Kitap Adi</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {defKitapList.map((defKitap, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${defKitap.id}`} color="link" size="sm">
                      {defKitap.id}
                    </Button>
                  </td>
                  <td>{defKitap.isbn}</td>
                  <td>{defKitap.kitapAdi}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${defKitap.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${defKitap.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${defKitap.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="startpointApp.defKitap.home.notFound">No Def Kitaps found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DefKitap;
