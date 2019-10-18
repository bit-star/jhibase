import { Moment } from 'moment';

export interface IHistorySearch {
  id?: number;
  searchConetnt?: string;
  searchCount?: number;
  isHot?: number;
  createdDate?: Moment;
  updateDate?: Moment;
}

export class HistorySearch implements IHistorySearch {
  constructor(
    public id?: number,
    public searchConetnt?: string,
    public searchCount?: number,
    public isHot?: number,
    public createdDate?: Moment,
    public updateDate?: Moment
  ) {}
}
