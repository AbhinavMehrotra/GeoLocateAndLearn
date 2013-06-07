package com.geolocateandlearn.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geolocateandlearn.R;
import com.geolocateandlearn.model.Challenge;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ChallengeListAdapter implements ListAdapter {
	final Context context;
	final List<Challenge> backingList;
	final Set<DataSetObserver> observers = new HashSet<DataSetObserver>();

	public ChallengeListAdapter(Context context,
			List<Challenge> selectedChallengesList) {
		this.context = context;
		backingList = selectedChallengesList;
	}

	public int getCount() {
		return backingList.size();
	}

	public Challenge getItem(int position) {
		return backingList.get(position);
	}

	public long getItemId(int position) {
		return backingList.get(position).getId();
	}

	public int getItemViewType(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.challenge_row, null);
		}
		final TextView challengeNameView = (TextView) v
				.findViewById(R.id.challenge_name);
		challengeNameView.setText(backingList.get(position).getName());
		return v;
	}

	public int getViewTypeCount() {
		return 1;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isEmpty() {
		return backingList.size() == 0;
	}

	public void registerDataSetObserver(DataSetObserver observer) {
		observers.add(observer);
	}

	public void unregisterDataSetObserver(DataSetObserver observer) {
		observers.remove(observer);
	}

	public boolean areAllItemsEnabled() {
		return true;
	}

	public boolean isEnabled(int position) {
		return true;
	}

}
