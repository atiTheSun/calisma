import { IDefType } from 'app/shared/model/def-type.model';

export interface IDefItem {
  id?: string;
  code?: string;
  name?: string;
  type?: IDefType;
  parent?: IDefItem | null;
}

export const defaultValue: Readonly<IDefItem> = {};
