export interface IDefKitap {
  id?: number;
  isbn?: string;
  kitapAdi?: string;
}

export const defaultValue: Readonly<IDefKitap> = {};
