package edu.wit.cs.comp3370;

// TODO: document class
public class BST extends LocationHolder {
	
	public BST() {
		root = nil;
	}
	
	@Override
	public DiskLocation next(DiskLocation d) {
		if(d.right != nil) {
			return d.right;
		}
		return up(d);
	}
	
	private DiskLocation up(DiskLocation d) {
		DiskLocation parent = d.parent;
		if(parent == nil || d.equals(parent.left)) {
			return parent;
		}
		return up(parent);
	}
	
	@Override
	public DiskLocation prev(DiskLocation d) {
		if(d.left != nil) {
			return d.left;
		}
		return up2(d);
	}
	
	private DiskLocation up2(DiskLocation d) {
		DiskLocation parent = d.parent;
		if(parent == nil || d.equals(parent.right)) {
			return parent;
		}
		return up2(parent);
	}

	@Override
	public void insert(DiskLocation d) {
		if(d.left == null) {
			d.left = nil;
		}
		if(d.right == null) {
			d.right = nil;
		}
		d.parent = findParent(d, root, nil);
		if(d.parent == nil) {
			root = d;
		} else {
			if(d.track < d.parent.track) {
				d.parent.left = d;
			} else {
				d.parent.right = d;
			}
		}		
	}
	
	private DiskLocation findParent(DiskLocation n, DiskLocation current, DiskLocation parent) {
		if(current == nil) {
			return parent;
		}
		int comparison = compare(n, current);
		if(comparison < 0) {
			return findParent(n, current.left, current);
		}
		return findParent(n, current.right, current);
	}

	@Override
	public int height() {
		int height = getBranchHeight(root) - 1;
		if(height < 0) {
			height = 0;
		}
		return height;
	}
	
	private int getBranchHeight(DiskLocation d) {
		if(d == nil) {
			return 0;
		}
		int height = 1;
		int l = getBranchHeight(d.left);
		int r = getBranchHeight(d.right);
		if(l > r) {
			return height + l;
		}
		return height + r;
	}

	@Override
	public DiskLocation find(DiskLocation d) {
		return findInTree(d, root);
	}
	
	private DiskLocation findInTree(DiskLocation d, DiskLocation root) {
		if(root == nil) {
			return nil;
		}
		
		int comparison = compare(d, root);
		if(comparison == 0) {
			return root;
		} else if(comparison < 0) {
			return findInTree(d, root.left);
		} else {
			return findInTree(d, root.right);
		}
	}
	
	private int compare(DiskLocation a, DiskLocation b) {
		if(a.equals(b)) {
			return 0;
		}
		if(a.track == b.track) {
			if(a.sector < b.sector) {
				return -1;
			}
			return 1;
		}
		if (a.track < b.track) {
			return -1;
		}
		return 1;
	}

}
