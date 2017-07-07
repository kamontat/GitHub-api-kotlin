package com.kamontat.github;

import com.kamontat.github.model.link.GithubLink;

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 04/Jul/2017 - 3:33 PM
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(GithubLink.Factory.create().REPOS().get());
	}
}
